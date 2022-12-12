package com.bernardpaula.lojaEletrodomesticos.service.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {

	
	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}
	
	
}
