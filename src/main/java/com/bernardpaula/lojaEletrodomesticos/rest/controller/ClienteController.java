package com.bernardpaula.lojaEletrodomesticos.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardpaula.lojaEletrodomesticos.domain.Cliente;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.ClienteCompletoDTO;
import com.bernardpaula.lojaEletrodomesticos.service.ClienteService;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

	private ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	
	@GetMapping("/buscar")
	public Cliente find(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		return obj;
	}
	
	@PostMapping
	public Cliente insert(@RequestBody ClienteCompletoDTO clienteDTO) {
		Cliente cliente = service.convertDTO(clienteDTO);
	}
}
