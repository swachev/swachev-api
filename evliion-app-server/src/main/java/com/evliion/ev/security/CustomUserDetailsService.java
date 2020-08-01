package com.evliion.ev.security;

import com.evliion.ev.exception.ResourceNotFoundException;
import com.evliion.ev.model.User;
import com.evliion.ev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        return UserPrincipal.create(new User());
    }

    @Transactional
    public UserDetails loadUserByMobileNumber(String mobileNumberOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either mobilenumber or email
        User user = userRepository.findByMobileNumberOrEmail(mobileNumberOrEmail, mobileNumberOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with mobile number or email : " + mobileNumberOrEmail)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}