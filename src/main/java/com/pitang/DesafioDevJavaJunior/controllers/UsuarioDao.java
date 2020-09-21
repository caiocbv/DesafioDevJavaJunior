package com.pitang.DesafioDevJavaJunior.controllers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.DesafioDevJavaJunior.model.entities.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
	    public Usuario findByEmail(String email);
	    public Usuario findByLogin(String login);
}
