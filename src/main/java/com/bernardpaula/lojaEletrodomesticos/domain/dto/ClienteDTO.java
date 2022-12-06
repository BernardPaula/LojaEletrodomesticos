package com.bernardpaula.lojaEletrodomesticos.domain.dto;

import com.bernardpaula.lojaEletrodomesticos.domain.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {

	private Integer id;
	
	private String nome;
	
	private String email;
	
	
	public ClienteDTO(){
		
	}
	
	public ClienteDTO(Cliente dto) {
		id = dto.getId();
		nome = dto.getNome();
		email = dto.getEmail();
	}
	
}
