package com.evliion.ev.service;

import com.evliion.ev.exception.BadRequestException;
import com.evliion.ev.model.User;
import com.evliion.ev.model.Wallet;
import com.evliion.ev.payload.marqeta.CreateUserRequest;
import com.evliion.ev.repository.UserRepository;
import com.evliion.ev.repository.WalletRepository;
import com.evliion.ev.security.UserPrincipal;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
  private UserRepository userRepository;
  private MarqetaService marqetaService;
  private WalletRepository walletRepository;
  private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

  public WalletService(UserRepository userRepository, MarqetaService marqetaService,
                       WalletRepository walletRepository) {
    this.userRepository = userRepository;
    this.marqetaService = marqetaService;
    this.walletRepository = walletRepository;
  }

  public void activateWallet() {
    User user = getCurrentUser();

    Optional<Wallet> walletOptional = walletRepository.findById(user.getId());

    //User wallet activation already happened
    if (walletOptional.isPresent()) {
      Wallet wallet = walletOptional.get();

      //Partial activation happened
      if (Objects.nonNull(wallet.getUserToken()) && (Objects.isNull(wallet.getCardToken()))) {
        wallet.setCardToken(marqetaService.createCard(wallet.getUserToken()));
        walletRepository.save(wallet);
      } else if (Objects.nonNull(wallet.getUserToken()) && Objects.nonNull(wallet.getCardToken())) {
        //wallet already activated
        throw new BadRequestException("User wallet activated already");
      }
    } else {
      activateWallet(user);
    }
  }

  private void activateWallet(User user) {
    CreateUserRequest userRequest = new CreateUserRequest(user.getEmail(), user.getName());
    String userToken = marqetaService.createUser(userRequest);
    Wallet wallet;
    try {
      String cardToken = marqetaService.createCard(userToken);
      wallet = new Wallet(user, userToken, cardToken);
      walletRepository.save(wallet);
    } catch (RuntimeException e) {
      wallet = new Wallet(user, userToken, null);
      walletRepository.save(wallet);
      throw e;
    }
  }

  private User getCurrentUser() {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Optional<User> user = userRepository.findById(userPrincipal.getId());
    if (!user.isPresent()) {
      logger.error("Not able to find user id '" + userPrincipal.getId() + "' set in authentication");
      throw new RuntimeException("Authenticated user not found");
    }
    return user.get();
  }
}
