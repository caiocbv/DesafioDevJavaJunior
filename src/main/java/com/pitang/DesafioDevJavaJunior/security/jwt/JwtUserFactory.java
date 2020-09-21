package com.pitang.DesafioDevJavaJunior.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.pitang.DesafioDevJavaJunior.model.entities.Usuario;
import com.pitang.DesafioDevJavaJunior.model.enums.ProfileEnum;

public class JwtUserFactory {

	private JwtUserFactory() {
	}
	
	public static JwtUser create(Usuario user) {
		return new JwtUser(user.getId().toString(), user.getLogin(), user.getPassword(),
				mapToGrantedAuthorities(user.getProfile()));
	}
	
	public static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profileEnum){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(profileEnum.toString()));
		return authorities;
	}
}
