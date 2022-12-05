package com.bernardpaula.lojaEletrodomesticos.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrors {
	
	public List<String> errors;
	
	public ApiErrors(String msg) {
		this.errors = Arrays.asList(msg);
	}

}
