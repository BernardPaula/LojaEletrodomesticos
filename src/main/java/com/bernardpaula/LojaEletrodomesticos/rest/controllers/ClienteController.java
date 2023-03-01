package com.bernardpaula.LojaEletrodomesticos.rest.controllers;

import com.bernardpaula.LojaEletrodomesticos.domain.Cliente;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.ClienteDTO;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.ClienteInsertDTO;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.ClienteListDTO;
import com.bernardpaula.LojaEletrodomesticos.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

	private ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@GetMapping("/buscar/{id}")
	public Cliente find(@PathVariable Integer id) {
		Cliente cliente = service.find(id);
		return cliente;
	}
	
	@PostMapping("/inserir")
	@ResponseStatus(HttpStatus.CREATED)   // 201
	public Cliente insert(@RequestBody @Valid ClienteInsertDTO cliDTO) {
		Cliente cli = service.convertDTO(cliDTO);
		cli = service.save(cli);
		return cli;
	}
	
	
	@PutMapping("/atualizar/{id}")
	public Cliente update(@RequestBody @Valid ClienteDTO cliDTO, @PathVariable Integer id) {
		Cliente cliente = service.updateClienteDTO(cliDTO);
		cliente = service.update(cliente, id);
		return cliente;
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)   // 204
	public void delete(@PathVariable Integer id) {
		  service.delete(id);
	}
	
	@GetMapping("/listar")
	public List<ClienteListDTO> findAll(){
		List<Cliente> list = service.findAll();
		List<ClienteListDTO> listDTO = list.stream().map(cliente -> new ClienteListDTO(cliente)).collect(Collectors.toList());
		return listDTO;
	}
	
	@GetMapping("/page")
	public Page<ClienteListDTO> findPage(
			@RequestParam(name = "page", defaultValue = "0")Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "nome")String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC")String direction){
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteListDTO> listDTO = list.map(cli -> new ClienteListDTO(cli));
		return listDTO;
	}
	
	@GetMapping("/filtrar/{filtro}")
	public List<ClienteListDTO> filtrarPorNome(@PathVariable String filtro){
		List<Cliente> list = service.filtrar(filtro);
		List<ClienteListDTO> listDTO = list.stream().map(cli -> new ClienteListDTO(cli)).collect(Collectors.toList());
		return listDTO;
	}
	
}
