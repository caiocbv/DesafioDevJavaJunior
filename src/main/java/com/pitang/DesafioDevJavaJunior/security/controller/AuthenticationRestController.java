package com.pitang.DesafioDevJavaJunior.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.DesafioDevJavaJunior.controllers.UsuarioDao;
import com.pitang.DesafioDevJavaJunior.model.entities.Usuario;
import com.pitang.DesafioDevJavaJunior.security.jwt.JwtAuthenticationRequest;
import com.pitang.DesafioDevJavaJunior.security.jwt.JwtTokenUtil;
import com.pitang.DesafioDevJavaJunior.security.model.CurrentUser;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UsuarioDao userDao;
	
	@Autowired
	PasswordEncoder passEncoder;

	@GetMapping(value = "/api/signin/")
	public JwtAuthenticationRequest getJwtAuthExample() {
		return new JwtAuthenticationRequest("email", "password");
	}

	@PostMapping(value = "/api/signin/")
	public @ResponseBody ResponseEntity<?> createAuthenticationToken(
			@Valid JwtAuthenticationRequest authenticationRequest) {
		if (authenticationRequest != null) {
			System.out.println("============>" + authenticationRequest.getEmail() + " senha recebida"
					+ authenticationRequest.getPassword());
			if (this.userDao.findByEmail(authenticationRequest.getEmail()) != null) {
				System.out.println("Senha do usuario no banco: "
						+ this.userDao.findByEmail(authenticationRequest.getEmail()).getPassword());
			}
		}
		
		try {
			final Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
							authenticationRequest.getPassword()));

			System.out.println("passou");

			SecurityContextHolder.getContext().setAuthentication(authentication);
			final UserDetails userDetails = this.userDetailsService
					.loadUserByUsername(authenticationRequest.getEmail());
			System.out.println("UserDetails =>" + userDetails.getPassword());
			final String token = jwtTokenUtil.generateToken(userDetails);
			//System.out.println("Token: " + token);
			final Usuario user = userDao.findByEmail(authenticationRequest.getEmail());
			user.setPassword(null);
			return ResponseEntity.ok(new CurrentUser(token, user));
		} catch (AuthenticationException e) {
			System.out.println(" =============================== Erro encontrado ================================ ");
			System.out.println(e.getMessage());
			System.out.println(" ================================================================================ ");

			throw e;
		}
	}

	@PostMapping(value = "api/refresh")
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String email = jwtTokenUtil.getUsernameFromToken(token);
		final Usuario user = userDao.findByEmail(email);

		if (jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = this.jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new CurrentUser(refreshedToken, user));
		} else
			return ResponseEntity.badRequest().body(null);

	}

}
