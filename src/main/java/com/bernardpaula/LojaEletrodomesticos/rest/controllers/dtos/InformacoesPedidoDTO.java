package com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String dataPedido;
	private Double total;
	private String cpfOuCnpj;
	private String estadoPagamento;
	private String nomeCliente;
	
	List<InformacoesItemPedidoDTO> itens;
	
	
}
