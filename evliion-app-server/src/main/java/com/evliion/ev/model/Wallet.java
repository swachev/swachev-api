package com.evliion.ev.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Wallet {

  @Id
  private Long userId;

  @OneToOne
  @MapsId
  private User user;

  @NotBlank
  private String userToken;

  private String cardToken;

  public Wallet() {

  }

  public Wallet(User user, String userToken, String cardToken) {
    this.user = user;
    this.userId = user.getId();
    this.userToken = userToken;
    this.cardToken = cardToken;
  }

  public String getUserToken() {
    return userToken;
  }

  public String getCardToken() {
    return cardToken;
  }

  public void setCardToken(String cardToken) {
    this.cardToken = cardToken;
  }
}
