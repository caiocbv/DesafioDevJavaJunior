package com.pitang.DesafioDevJavaJunior.model.entities;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pitang.DesafioDevJavaJunior.model.enums.ProfileEnum;
import com.sun.istack.NotNull;




@Entity 
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	private String firstName; // Nome do usuário
	@NotEmpty
	private String lastName; // Sobrenome do usuário
	@NotEmpty
	@Email
	@Column(unique = true)
	private String email; // E-mail do usuário
	@NotNull
	private Date  birthday; // Data de nascimento do usuário
	@NotEmpty
	@Column(unique = true)
	private String login; // Login do usuário
	@NotEmpty
	private String password; // Senha do usuário
	@NotEmpty
	private String phone; // Telefone do usuário
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Carro> cars; // Lista de carros do usuário
	
	@JsonIgnore
	private ProfileEnum profile;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String firstName, String lastName, String email, Date  birthday, String login,
			String password, String phone, ArrayList<Carro> cars) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	    this.birthday = birthday;
		this.login = login;
		this.password = password;
		this.phone = phone;
		this.cars = cars;
		this.profile = ProfileEnum.ROLE_ADMIN;
	}

	
	
	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}

	public Integer getId() {
		return id;
	}

	public List<Carro> getCars() {
		return cars;
	}

	public void setCars(List<Carro> cars) {
		this.cars = cars;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date  getBirthday() {
		return birthday;
	}

	public void setBirthday(Date  birthday) {
		this.birthday = birthday;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	


}
