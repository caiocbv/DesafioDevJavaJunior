package com.pitang.DesafioDevJavaJunior.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Carro {
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	@Min(0)
	private Integer year; // Ano de fabricação do carro
	
	@NotEmpty
	private String licensePlate; // Placa do carro
	
	@NotEmpty
	private String model; // Modelo do carro
	@NotEmpty
	private String color;// Cor predominante do carro

	public Carro() {
		// TODO Auto-generated constructor stub
	}
	

	public Carro(@Min(0) Integer year, @NotEmpty String licensePlate, @NotEmpty String model, @NotEmpty String color) {
		super();
		this.year = year;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
	}


	public Integer getId() {
		return id;
	}


	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
