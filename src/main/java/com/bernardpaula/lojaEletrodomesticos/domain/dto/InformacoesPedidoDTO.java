package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {

	private Integer codigoPedido;

	//private Pagamento pagamento;
	private String nomeCliente;
	private String dataPedido;
	private String cpfOuCnpj;
	private Integer estadoPagamento;
	private BigDecimal total;
	private List<InformacoesItemPedidoDTO> itensPedidos;

}
