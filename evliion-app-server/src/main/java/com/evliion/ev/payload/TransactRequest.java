package com.evliion.ev.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TransactRequest {
  @Positive
  @NotNull
  private Double amount;
  private String currency;
  private String merchantId;

  public TransactRequest(@Positive @NotNull Double amount, String currency, String merchantId) {
    this.amount = amount;
    this.currency = currency;
    this.merchantId = merchantId;
  }

  public String getMerchantId() {
    return merchantId;
  }

  public Double getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }
}
