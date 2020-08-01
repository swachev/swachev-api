package com.evliion.ev.payload;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class UserSummary {
    private Long id;
    private String mobileNumber;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSummary(Long id, String mobileNumber, String name) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.name = name;
        
    }
    public UserSummary(Long id, String mobileNumber, String name, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.mobileNumber = mobileNumber;
		this.name = name;
		this.authorities = authorities;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
    
}
