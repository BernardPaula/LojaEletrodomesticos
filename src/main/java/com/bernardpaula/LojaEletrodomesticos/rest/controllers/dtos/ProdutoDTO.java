package com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
public class ProdutoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "O campo nome é obrigatório")
	@Length(min=3, max=50, message = "O tamanho do nome deve estar entre 3 e 50 caracteres")
	private String nome;

	@NotEmpty(message = "O campo descrição e obrigatório")
	@Length(min=3, max=500, message = "O tamanho da descreção deve estar entre 3 e 500 caracteres")
	private String descricao;

	@NotNull(message = "O campo preço é obrigatório")
	private Double preco;

}
