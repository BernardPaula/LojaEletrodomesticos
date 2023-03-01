package com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos;

import com.bernardpaula.LojaEletrodomesticos.validations.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoSaveDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotNull(message = "O campo total é obrigatório")
	private Double total;
	
	
	// Pagamento
	
	private Integer estadoPagamento;
	
		
	//Cliente id
	
	@NotNull(message = "O campo id do cliente é obrigatório")
	private Integer clienteId;
	
	@NotEmptyList
	private List<ItemPedidoDTO> itens;
	
	
	
	
}
