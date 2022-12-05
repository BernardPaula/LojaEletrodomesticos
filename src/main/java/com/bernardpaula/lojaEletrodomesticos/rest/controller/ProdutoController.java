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

import com.bernardpaula.lojaEletrodomesticos.domain.Produto;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.ProdutoDTO;
import com.bernardpaula.lojaEletrodomesticos.service.ProdutoService;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController {

	private ProdutoService service;
	
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}
	
	
	@GetMapping("/buscar/{id}")
	public Produto find(@PathVariable Integer id) {
	Produto obj = service.find(id);
	return obj;
	}
	
	@PostMapping("/inserir")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto insert(@RequestBody ProdutoDTO objDto) {
		objDto.setId(null);
		Produto produto = service.convertProdutoDTO(objDto);
		produto = service.insert(produto);
		return produto;
	}
	
	@PutMapping("/atualizar/{id}")
	public Produto update(@RequestBody ProdutoDTO objDto, @PathVariable Integer id) {
		Produto obj = service.convertProdutoDTO(objDto);
		Produto newObj = service.update(obj, id);
		return newObj;
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@GetMapping("/listar")
	public List<ProdutoDTO> findAll(){
		List<Produto> list = service.findAll();
		List<ProdutoDTO> listDto = list.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
		return listDto;
	}
	
	@GetMapping("/filtrar")
	public List<Produto> filtrar(@RequestBody Produto filtro){
		List<Produto> list = service.filtrar(filtro);
		return list;
	}
	
	@GetMapping("/page")
	public Page<ProdutoDTO> findPage(
						@RequestParam(value = "page", defaultValue = "0") Integer page,
						@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
						@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
						@RequestParam(value = "direction", defaultValue = "ASC") String direction){
		Page<Produto> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return listDto;
	}
	
	
	
}
