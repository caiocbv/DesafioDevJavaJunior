package com.pitang.DesafioDevJavaJunior.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.DesafioDevJavaJunior.model.entities.Carro;
import com.pitang.DesafioDevJavaJunior.model.entities.Usuario;
import com.pitang.DesafioDevJavaJunior.model.enums.ProfileEnum;
import com.pitang.DesafioDevJavaJunior.model.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	UsuarioDao usuarioDao;

	@Autowired
	PasswordEncoder passEncoder;
	
//	@GetMapping
//	public Usuario teste() {
//		return new Usuario("figaro",
//				"fino",
//				"fino@gmail.com",
//				 new Date(123),
//				"figaro123",
//				"123123",
//				"123123123");
//	}

	/*
	 * Lista todos os usuários
	 */
	@GetMapping
	public Iterable<Usuario> listarUsuarios() {
		return this.usuarioRepository.findAll();
	}

	/*
	 * Cadastra um Novo usuário
	 */
	@PostMapping
	public @ResponseBody ResponseEntity novoUsuario(@Valid Usuario user) {
		if (user.getFirstName() != null && user.getLastName() != null && user.getBirthday() != null
				&& user.getPhone() != null && user.getEmail() != null && user.getLogin() != null
				&& user.getPassword() != null) {
			
			if(this.userEmailAlreadyExists(user.getEmail())) {
				return new ResponseEntity<>("{ \"message\": \"Email already exists\", \"errorCode\": " + 400 + " }",
						HttpStatus.BAD_REQUEST);
			}
			if(this.userLoginAlreadyExists(user.getLogin())) {
				return new ResponseEntity<>("{ \"message\": \"Login already exists\", \"errorCode\": " + 400 + " }",
						HttpStatus.BAD_REQUEST);
			}
			/*
			if(user.getCars() != null) {
				for ( Carro carro : user.getCars()) {
					CarroController.adicionarCarro(carro);
				}
			}*/
			user.setProfile(ProfileEnum.ROLE_COSTUMER);
			user.setPassword(passEncoder.encode(user.getPassword()));
			this.usuarioRepository.save(user);
			return new ResponseEntity<>("{ \"message\": \"Usuario Cadastrado com Sucesso\" }",
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("{ \"message\": \"Missing fields\", \"errorCode\": " + 400 + " }",
					HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * Busca usuario pelo Id
	 */
	@GetMapping("/{id}")
	public @ResponseBody Optional<Usuario> findUsuarioById(@PathVariable Integer id) {
		return this.usuarioRepository.findById(id);
	}

	/*
	 * Remove usuario pelo Id
	 */
	@DeleteMapping("/{id}")
	public @ResponseBody void deleteUsuarioById(@PathVariable Integer id) {
		this.usuarioRepository.deleteById(id);
	}

	/*
	 * Atualizar usuario pelo Id
	 */
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity atualizarUsuario(@Valid Usuario user, @PathVariable Integer id)
			throws Exception {
		
		Optional<Usuario> oldUser = this.usuarioRepository.findById(id);
		if (oldUser.isPresent()) {
			Usuario u = oldUser.get();
			if (user.getFirstName() != null && user.getLastName() != null && user.getBirthday() != null
					&& user.getPhone() != null && user.getEmail() != null && user.getLogin() != null
					&& user.getPassword() != null) {
				u.setFirstName(user.getFirstName());
				u.setLastName(user.getLastName());
				u.setBirthday(user.getBirthday());
				u.setPhone(user.getPhone());
				u.setPassword(user.getPassword());
			} else {
				return new ResponseEntity<>("{ \"message\": \"Missing fields\", \"errorCode\": " + 400 + " }",
						HttpStatus.BAD_REQUEST);
			}
			if (!this.userEmailAlreadyExists(user.getEmail()) || u.getEmail().equals(user.getEmail())) {
				u.setEmail(user.getEmail());
			} else {
				return new ResponseEntity<>("{ \"message\": \"Email already exists\", \"errorCode\": " + 400 + " }",
						HttpStatus.BAD_REQUEST);
			}

			if (!this.userLoginAlreadyExists(user.getLogin()) || u.getLogin().equals(user.getLogin())) {
				u.setLogin(user.getLogin());
			} else {
				return new ResponseEntity<>("{ \"message\": \"Login already exists\", \"errorCode\": " + 400 + " }",
						HttpStatus.BAD_REQUEST);
			}

			this.usuarioRepository.save(u);
			return new ResponseEntity<Usuario>(u, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("{ \"message\": \"User Not Found\", \"errorCode\": " + 404 + " }",
					HttpStatus.NOT_FOUND);
		}
	}

	private boolean userEmailAlreadyExists(String email) {
		Usuario user = this.usuarioDao.findByEmail(email);
		if (user == null) {
			return false;
		} else
			// System.out.println("Usuario Encontrado" + " " + user.getEmail());
			return true;

	}

	private boolean userLoginAlreadyExists(String login) {
		Usuario user = this.usuarioDao.findByLogin(login);
		if (user == null) {
			return false;
		} else
			// System.out.println("Usuario Encontrado" + " " + user.getEmail());
			return true;
	}

}
