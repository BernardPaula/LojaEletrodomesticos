package com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos;

import com.bernardpaula.LojaEletrodomesticos.domain.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ClienteListDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String nome;
	
	
	public ClienteListDTO(Cliente cli) {
		id = cli.getId();
		nome = cli.getNome();
	}
	
}
