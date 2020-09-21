package com.pitang.DesafioDevJavaJunior.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pitang.DesafioDevJavaJunior.model.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
