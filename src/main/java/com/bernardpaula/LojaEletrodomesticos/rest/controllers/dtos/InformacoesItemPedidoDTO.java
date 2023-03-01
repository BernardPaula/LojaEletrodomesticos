package com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InformacoesItemPedidoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer quantidade;
	
	private Double preco;

	private Double desconto;
	
}
