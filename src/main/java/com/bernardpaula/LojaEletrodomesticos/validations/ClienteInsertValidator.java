package com.bernardpaula.LojaEletrodomesticos.validations;

import com.bernardpaula.LojaEletrodomesticos.domain.Cliente;
import com.bernardpaula.LojaEletrodomesticos.domain.enums.TipoCliente;
import com.bernardpaula.LojaEletrodomesticos.repositories.ClienteRepository;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.ClienteInsertDTO;
import com.bernardpaula.LojaEletrodomesticos.rest.exceptions.FieldMessage;
import com.bernardpaula.LojaEletrodomesticos.validations.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteInsertDTO> {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Override
	public boolean isValid(ClienteInsertDTO objDto, ConstraintValidatorContext context) {
		

		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("Email", "Email ja existente"));
		}
		
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
		
	}

}
