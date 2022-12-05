package com.bernardpaula.lojaEletrodomesticos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bernardpaula.lojaEletrodomesticos.domain.Cliente;
import com.bernardpaula.lojaEletrodomesticos.repositories.ClienteRepository;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	private ClienteRepository repo;
	
	public ClienteService(ClienteRepository repo) {
		this.repo = repo;
	}
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar cliente"));
	}

}
