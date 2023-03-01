package com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AtualizarStatusPagamentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "O campo status do Pagamento é obrigatório")
	private Integer statusPagamento;
	
}
