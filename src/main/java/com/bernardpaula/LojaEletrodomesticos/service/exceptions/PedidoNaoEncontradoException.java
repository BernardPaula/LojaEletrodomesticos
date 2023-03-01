package com.bernardpaula.LojaEletrodomesticos.service.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException{

	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}
	
}
