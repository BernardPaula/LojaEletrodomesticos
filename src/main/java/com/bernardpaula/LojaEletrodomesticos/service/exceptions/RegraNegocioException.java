package com.bernardpaula.LojaEletrodomesticos.service.exceptions;

public class RegraNegocioException extends RuntimeException{

	public RegraNegocioException(String msg) {
		super(msg);
	}
	
	public RegraNegocioException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
