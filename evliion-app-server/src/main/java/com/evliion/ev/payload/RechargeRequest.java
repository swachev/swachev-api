package com.evliion.ev.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class RechargeRequest {
  @Positive
  @NotNull
  private Double amount;
  private String currency;

  public RechargeRequest(@Positive @NotNull Double amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public Double getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }
}
