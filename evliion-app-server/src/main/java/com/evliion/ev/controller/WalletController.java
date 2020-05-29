package com.evliion.ev.controller;

import com.evliion.ev.payload.ApiResponse;
import com.evliion.ev.payload.RechargeRequest;
import com.evliion.ev.payload.TransactRequest;
import com.evliion.ev.service.WalletService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

  private WalletService walletService;

  public WalletController(WalletService walletService) {
    this.walletService = walletService;
  }

  @PostMapping("/activate")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse> activateWallet() {
    walletService.activateWallet();
    return new ResponseEntity<>(new ApiResponse(true, "Wallet activated successfully"), HttpStatus.OK);
  }

  @PostMapping("/recharge")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse> rechargeWallet(@RequestBody @Valid RechargeRequest rechargeRequest) {
    return new ResponseEntity<>(new ApiResponse(true, "Wallet recharged successfully"), HttpStatus.OK);
  }

  @PostMapping("/transact")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse> transactWallet(@RequestBody @Valid TransactRequest transactRequest) {
    return new ResponseEntity<>(new ApiResponse(true, "Wallet transacted successfully"), HttpStatus.OK);
  }
}
