package com.pitang.DesafioDevJavaJunior.security.model;

import org.springframework.stereotype.Component;

import com.pitang.DesafioDevJavaJunior.model.entities.Usuario;


public class CurrentUser {
	private String token;
	private Usuario user;
	public CurrentUser(String token, Usuario user) {
		super();
		this.token = token;
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	
	
}
