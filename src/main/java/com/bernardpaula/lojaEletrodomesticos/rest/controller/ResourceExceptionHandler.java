package com.bernardpaula.lojaEletrodomesticos.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bernardpaula.lojaEletrodomesticos.rest.ApiErrors;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.DataIntegrityException;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.ObjectNotFoundException;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.RegraNegocioException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) //404
	public ApiErrors ObjectNotFoundHandler(ObjectNotFoundException e) {
		return new ApiErrors(e.getMessage());
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)  //400 -> sintaxe inválida
	public ApiErrors DataIntegrityExceptionHandler(DataIntegrityException e) {
		return new ApiErrors(e.getMessage());
	}
	
	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors RegraNegocioExceptionHandler(RegraNegocioException e) {
		return new ApiErrors(e.getMessage());
	}
}
