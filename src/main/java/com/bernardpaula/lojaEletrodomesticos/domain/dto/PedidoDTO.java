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
public class PedidoDTO {

private Integer id;
	
	
	private Pagamento pagamento;
	
	private Integer estadoPagamento;
	
	private BigDecimal total;
	
	private Integer clienteId;
		
	private List<ItemPedidoDTO> itensPedidos;
	
}
