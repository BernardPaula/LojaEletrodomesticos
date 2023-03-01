package com.bernardpaula.LojaEletrodomesticos.rest.exceptions;


import com.bernardpaula.LojaEletrodomesticos.service.exceptions.ObjectNotFoundException;
import com.bernardpaula.LojaEletrodomesticos.service.exceptions.PedidoNaoEncontradoException;
import com.bernardpaula.LojaEletrodomesticos.service.exceptions.RegraNegocioException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	/*
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)  // 400 - Significa que o servidor não entendeu a requisição pois está com uma síntaxe inválida
	public ApiErrors ObjectNotFoundHandler(ObjectNotFoundException e) {
		
		return new ApiErrors(e.getMessage());
	}
	*/
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	
	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<StandardError> RegraNegocioExceptionHandler(RegraNegocioException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(PedidoNaoEncontradoException.class)
	public ResponseEntity<StandardError> PedidoNaoEncontradoExceptionHandler(PedidoNaoEncontradoException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}


	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
	
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de Validação", System.currentTimeMillis());
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}		
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
