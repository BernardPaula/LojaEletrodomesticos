package com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer quantidade;
	
	private Double preco;

	private Double desconto;
	
	
	//Produto id
	
	private Integer produtoId;
	
}
