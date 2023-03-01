package com.bernardpaula.LojaEletrodomesticos.rest.controllers;

import com.bernardpaula.LojaEletrodomesticos.domain.Produto;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.ProdutoDTO;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.ProdutoListDTO;
import com.bernardpaula.LojaEletrodomesticos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

	private ProdutoService service;
	
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}
	
	@GetMapping("/buscar/{id}")
	public Produto find(@PathVariable Integer id) {
		Produto prod = service.find(id);
		return prod;
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto insert(@RequestBody @Valid ProdutoDTO proDTO) {
		proDTO.setId(null);
		Produto pro = service.convertDTO(proDTO);
		pro = service.save(pro);
		return pro;
	}
	
	@PutMapping("/atualizar/{id}")
	public Produto update(@RequestBody @Valid ProdutoDTO dto, @PathVariable Integer id) {
		Produto pro = service.convertDTO(dto);
		pro = service.update(pro, id);
		return pro;
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@GetMapping("/listar")
	public List<ProdutoListDTO> findAll(){
		List<Produto> list = service.findAll();
		List<ProdutoListDTO> listDTO = list.stream().map(obj -> new ProdutoListDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}
	
	@GetMapping("/page")
	public Page<ProdutoListDTO> findPage(
			@RequestParam(value = "page", defaultValue = "0")Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC")String direction){
		Page<Produto> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ProdutoListDTO> listDTO = list.map(obj -> new ProdutoListDTO(obj));
		return listDTO;
	}
	
	@GetMapping("/filtrar/{filtro}")
	public List<ProdutoListDTO> filtrar(@PathVariable String filtro){
		List<Produto> list = service.filtrar(filtro);
		List<ProdutoListDTO> listDTO = list.stream().map(obj -> new ProdutoListDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}
	
}
