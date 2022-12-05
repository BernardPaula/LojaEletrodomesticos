package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import com.bernardpaula.lojaEletrodomesticos.domain.Categoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDTO {

	private Integer id;
	
	private String nome;
	
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
	
}
