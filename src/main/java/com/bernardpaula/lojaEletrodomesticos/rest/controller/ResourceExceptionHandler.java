package com.bernardpaula.lojaEletrodomesticos.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bernardpaula.lojaEletrodomesticos.rest.ApiErrors;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors ObjectNotFoundHandler(ObjectNotFoundException e) {
		return new ApiErrors(e.getMessage());
	}
}
