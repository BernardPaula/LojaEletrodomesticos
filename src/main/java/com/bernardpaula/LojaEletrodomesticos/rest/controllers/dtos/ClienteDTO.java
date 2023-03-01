package com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos;

import com.bernardpaula.LojaEletrodomesticos.validations.ClienteUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ClienteUpdate
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3 , max = 120 , message = "O tamanho deve ser entre 3 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	
}
