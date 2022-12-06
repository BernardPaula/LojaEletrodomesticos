package com.bernardpaula.lojaEletrodomesticos.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bernardpaula.lojaEletrodomesticos.domain.Cliente;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.ClienteCompletoDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.ClienteDTO;
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
	
	@PostMapping("/inserir")
	@ResponseStatus(HttpStatus.CREATED)  //201
	public Cliente insert(@RequestBody ClienteCompletoDTO clienteDTO) {
		Cliente cliente = service.convertDTO(clienteDTO);
		cliente = service.save(cliente);
		return cliente;
	}
	
	@PutMapping("/atualizar/{id}")
	public Cliente update(@RequestBody ClienteDTO clienteDto, @PathVariable Integer id) {
		Cliente cliente = service.converterClienteDTO(clienteDto);
		Cliente cliAtualizado = service.update(cliente,id);
		return cliAtualizado;
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)  //204
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@GetMapping("/listar")
	public List<ClienteDTO> findAll(){
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDTO = list.stream().map( obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}
	
	@GetMapping("/filtrar/{palavra}")
	public List<Cliente> filtrar(@PathVariable String palavra){
		List<Cliente> list = service.filtrar(palavra);
		return list;
	}
	
	@GetMapping("/page")
	public Page<ClienteDTO> findPage(
			@RequestParam(value = "page", defaultValue = "0")Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction){
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return listDto;
	}
	
}
