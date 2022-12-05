package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {

	private Integer quantidade;

	private Double desconto;

	private Double preco;

	private Integer produtoId;
	
}
