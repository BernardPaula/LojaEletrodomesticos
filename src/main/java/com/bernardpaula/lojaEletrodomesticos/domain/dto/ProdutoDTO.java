package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import com.bernardpaula.lojaEletrodomesticos.domain.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

	private Integer id;

	private String nome;

	private String marca;

	private String descricao;

	private Double preco;

	public ProdutoDTO(Produto obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
		marca = obj.getMarca();
		descricao = obj.getDescricao();
		preco = obj.getPreco();
	}
	
	
	
	
}
