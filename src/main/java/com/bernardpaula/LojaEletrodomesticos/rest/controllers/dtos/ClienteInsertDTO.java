package com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos;

import com.bernardpaula.LojaEletrodomesticos.validations.ClienteInsert;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ClienteInsert
public class ClienteInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	// Cliente

	@NotEmpty(message = "O campo nome é obrigatório")
	@Length(min = 3, max=50, message = "O tamanho do nome deve estar entre 3 e 50 caracteres.")
	private String nome;

	@NotEmpty(message = "O campo email é obrigatório")
	@Email(message = "Email inválido")
	private String email;

	private String CpfOuCnpj;

	@NotNull(message = "O campo tipo é obrigatório")
	private Integer tipo;

	
	// Endereco

	@NotEmpty(message = "O campo rua é obrigatório.")
	private String rua;

	@NotEmpty(message = "O campo bairro é obrigatório")
	private String bairro;

	@NotNull(message = "O campo numero é obrigatório")
	private Integer numero;

	@NotEmpty(message = "O campo cidade é obrigatório")
	private String cidade;

	@NotEmpty(message = "O campo estado é obrigatório")
	private String estado;

	
	// telefones
	
	@NotEmpty(message = "O campo telefone é obrigatório.")
	@Length(min=3, max=20, message = "O tamanho do campo telefone deve ter entre 3 e 20 caracteres")
	private String telefone1;
	
	private String telefone2;
	private String telefone3;
	
}
