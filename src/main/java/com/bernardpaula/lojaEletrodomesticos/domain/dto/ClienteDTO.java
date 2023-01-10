package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import org.hibernate.validator.constraints.Length;

import com.bernardpaula.lojaEletrodomesticos.domain.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {

	private Integer id;
	
	@NotEmpty(message="preenchimento obrigatório")
	@Length(min=5, max=120, message= "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message= "preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	
	public ClienteDTO(){
		
	}
	
	public ClienteDTO(Cliente dto) {
		id = dto.getId();
		nome = dto.getNome();
		email = dto.getEmail();
	}
	
}
