package com.pitang.DesafioDevJavaJunior.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pitang.DesafioDevJavaJunior.controllers.UsuarioDao;
import com.pitang.DesafioDevJavaJunior.model.entities.Usuario;
import com.pitang.DesafioDevJavaJunior.model.repositories.UsuarioRepository;
import com.pitang.DesafioDevJavaJunior.security.jwt.JwtUserFactory;

@Service
public class JwtUserDetailService implements UserDetailsService {
	
	@Autowired
	UsuarioDao usuarioDao; 

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = this.usuarioDao.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("No User Found with this email" + email + ".s");
		}
		return JwtUserFactory.create(user);
	}
	
}
