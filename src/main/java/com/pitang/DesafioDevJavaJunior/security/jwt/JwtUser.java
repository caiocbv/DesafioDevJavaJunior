package com.pitang.DesafioDevJavaJunior.security.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID =-7471177468146141709L;
	
	private final String id;
	private final String login;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
	
	
	
	public JwtUser(String id, String login, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.authorities = authorities;
	}
	
	
	@JsonIgnore
	public String getId() {
		return id;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public String getUsername() {
		return login;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	
	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	

	@Override
	public boolean isEnabled() {
		return true;
	}

}
