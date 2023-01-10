package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import java.math.BigDecimal;
import java.util.List;

import com.bernardpaula.lojaEletrodomesticos.validation.NotEmptyList;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

private Integer id;
	
	@NotNull(message = "{campo.estado-pagamento.obrigatorio}")
	private Integer estadoPagamento;
	
	@NotNull(message = "{campo.total-pedido.obrigatorio}")
	private Double total;
	
	@NotNull(message = "{campo.cliente-id.obrigatorio}")
	private Integer clienteId;
		
	@NotEmptyList(message = "{campo.list.itens-pedido.obrigatorio}")
	private List<ItemPedidoDTO> itensPedidos;
	
}
