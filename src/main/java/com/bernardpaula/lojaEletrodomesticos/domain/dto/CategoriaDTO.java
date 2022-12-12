package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import org.hibernate.validator.constraints.Length;

import com.bernardpaula.lojaEletrodomesticos.domain.Categoria;

import jakarta.validation.constraints.NotEmpty;
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
	
	@NotEmpty(message = "campo.nome.obrigatorio")
	@Length(min=1, max=30, message = "O tamanho do nome deve estar entre 1 e 30 caracteres")
	private String nome;
	
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
	
}
