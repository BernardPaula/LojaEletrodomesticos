package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesItemPedidoDTO {


	private Integer quantidade;

	private Double desconto;
	
	private String descricao;

	private Double precoUnitario;
	
}
