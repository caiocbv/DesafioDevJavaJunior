package com.pitang.DesafioDevJavaJunior.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.DesafioDevJavaJunior.model.entities.Carro;
import com.pitang.DesafioDevJavaJunior.model.repositories.CarroRepository;
import com.pitang.DesafioDevJavaJunior.model.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/cars")
public class CarroController {

	@Autowired
	CarroRepository carroRepository;
	
	public static void adicionarCarro(Carro carro) {
		
	}

}
