package com.pitang.DesafioDevJavaJunior.security.jwt;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	
	public JwtAuthenticationRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public JwtAuthenticationRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
