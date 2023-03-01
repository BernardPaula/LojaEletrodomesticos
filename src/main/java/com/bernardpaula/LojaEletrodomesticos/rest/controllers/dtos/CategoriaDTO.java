package com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos;


import com.bernardpaula.LojaEletrodomesticos.domain.Categoria;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

	private Integer id;
	
	@NotEmpty(message = "O preenchimeto do campo nome é obrigatório.")
	@Length(min=3, max=50, message = "O tamanho do nome deve estar entre 3 e 50 caracteres.")
	private String nome;
	
	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}
			
	
}
