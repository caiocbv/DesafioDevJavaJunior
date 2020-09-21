package com.pitang.DesafioDevJavaJunior.controllers.exceptions;

import java.lang.annotation.Annotation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mysql.cj.xdevapi.JsonArray;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	
	private String message = "User Not Found";
	private Integer code = 0;
	
	public UserNotFoundException() {
		super();
	}
	@Override
	public String getMessage() {	
		return "{ \"message\": " + this.message + ", \"errorCode\": " + code + " }";
	}
}