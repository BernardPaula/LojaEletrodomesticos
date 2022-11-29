package com.bernardpaula.lojaEletrodomesticos.domain;

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
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento{

	private Integer numeroParcelas;
	
}
