package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import java.math.BigDecimal;
import java.util.List;

import com.bernardpaula.lojaEletrodomesticos.domain.Endereco;
import com.bernardpaula.lojaEletrodomesticos.domain.Pagamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCompletoDTO {

private Integer id;
	
	private String dataPedido;
	
	private Pagamento pagamento;
	
	private BigDecimal total;
	
	private Endereco enderecoDeEntrega;
	
	private Integer estado;
	
	
	//---- Cliente
		private Integer clienteId;
		
		private List<ItemPedidoDTO> itensPedidos;
		
		
	//--- EnderecoDeEntrega
	/*
	private Integer idEndereco;
	
	private String logradouroEndereco;
	
	private String numeroEncereco;
	
	private String complementoEndereco;
	
	private String bairroEndereco;
	
	private String cepEndereco;
	
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;
	*/
	
	
	
}
