package com.evliion.ev.controller;

import com.evliion.ev.payload.ApiResponse;
import com.evliion.ev.payload.RechargeRequest;
import com.evliion.ev.payload.TransactRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

  @PostMapping("/activate")
  public ResponseEntity<ApiResponse> activateWallet() {
    return new ResponseEntity<>(new ApiResponse(true, "Wallet activated successfully"), HttpStatus.OK);
  }

  @PostMapping("/recharge")
  public ResponseEntity<ApiResponse> rechargeWallet(@RequestBody @Valid RechargeRequest rechargeRequest) {
    return new ResponseEntity<>(new ApiResponse(true, "Wallet recharged successfully"), HttpStatus.OK);
  }

  @PostMapping("/transact")
  public ResponseEntity<ApiResponse> transactWallet(@RequestBody @Valid TransactRequest transactRequest) {
    return new ResponseEntity<>(new ApiResponse(true, "Wallet transacted successfully"), HttpStatus.OK);
  }
}
