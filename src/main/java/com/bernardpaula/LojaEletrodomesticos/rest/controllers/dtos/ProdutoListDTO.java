package com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos;

import com.bernardpaula.LojaEletrodomesticos.domain.Produto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProdutoListDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;
	
	
	public ProdutoListDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
	
}
