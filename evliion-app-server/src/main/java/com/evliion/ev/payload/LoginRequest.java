package com.evliion.ev.payload;

import javax.validation.constraints.NotBlank;

/**
 * 
 */
public class LoginRequest {
    @NotBlank
    private String mobileNumberOrEmail;

    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getMobileNumberOrEmail() {
		return mobileNumberOrEmail;
	}

	public void setMobileNumberOrEmail(String mobileNumberOrEmail) {
		this.mobileNumberOrEmail = mobileNumberOrEmail;
	}
}
