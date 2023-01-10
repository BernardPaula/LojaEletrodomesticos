package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCompletoDTO {

	// cliente
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	@Length(min=3, max=50, message = "O campo nome deve ter entre 3 e 50 caracteres.")
	private String nome;

	@Email(message = "{campo.email.invalido}")
	@NotEmpty(message = "{campo.email.obriagatorio}")
	private String email;

	@NotEmpty(message = "{campo.cpf-ou-cnpj.obrigatorio}")
	private String cpfOuCnpj;

	@NotNull(message = "{campo.cliente-id.obrigatorio}")
	private Integer tipoCliente;
	

	// Endereco

	private String logradouro;

	@NotEmpty(message = "{campo.numero.obrigatorio}")
	private String numero;

	private String complemento;

	@NotEmpty(message = "{campo.bairro.obrigatorio}")
	private String bairro;

	@NotEmpty(message = "{campo.cep.obrigatorio}")
	private String cep;
	
	
	// Telefones
	@NotEmpty(message = "{campo.telefone.obrigatorio}")
	private String telefone1;
	
	private String telefone2;
	private String telefone3;
	
	// Cidade
	private Integer cidadeId;
	
}
