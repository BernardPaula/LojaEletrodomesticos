package com.bernardpaula.lojaEletrodomesticos.domain;

import com.bernardpaula.lojaEletrodomesticos.domain.enums.EstadoPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagamento_cartoes")
public class PagamentoCartao extends Pagamento{

	private Integer numeroParcelas;

	
	public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}


	
	
}
