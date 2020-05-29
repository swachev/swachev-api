package com.evliion.ev.payload.marqeta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserRequest {
  private String email;

  @JsonProperty("first_name")
  private String firstName;

  public CreateUserRequest(String email, String firstName) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
}
