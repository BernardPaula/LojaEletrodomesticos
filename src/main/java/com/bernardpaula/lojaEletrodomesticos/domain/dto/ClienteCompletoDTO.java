package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCompletoDTO {

	// cliente
	private String nome;

	private String email;

	private String cpfOuCnpj;

	private Integer tipoCliente;
	

	// Endereco
	//private Integer enderecoId;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;
	
	
	// Telefones
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	// Cidade
	private Integer cidadeId;
	
}
