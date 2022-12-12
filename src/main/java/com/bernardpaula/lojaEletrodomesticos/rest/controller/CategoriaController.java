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

import com.bernardpaula.lojaEletrodomesticos.domain.Categoria;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.CategoriaDTO;
import com.bernardpaula.lojaEletrodomesticos.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController {

	private CategoriaService service;
	
	public CategoriaController(CategoriaService service) {
		this.service = service;
	}
	
	
	@GetMapping("/buscar/{id}")
	public Categoria find(@PathVariable Integer id) {
		Categoria categoria = service.find(id);
		return categoria;
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria save(@RequestBody CategoriaDTO obj) {
		obj.setId(null);
		Categoria categoria = service.save(obj);
		return categoria;
	}
	
	@PutMapping("/atualizar/{id}")
	public Categoria update(@RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		Categoria obj = service.updateDTO(objDto);
		obj.setId(null);
		Categoria objAtualizado = service.update(obj, id);
		return objAtualizado;
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@GetMapping("/listar")
	public List<CategoriaDTO> findAll(){
		List<Categoria> lista = service.findAll();
		List<CategoriaDTO> listDTO = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}
	
	@GetMapping("/filtrar")
	public List<CategoriaDTO> filtrar(Categoria categoria){
		List<Categoria> list = service.filtrar(categoria);
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}
	
	@GetMapping("/page")
	public Page<CategoriaDTO> findPage(
			@RequestParam(value = "page", defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction){
		Page<Categoria> pageList = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO = pageList.map(obj -> new CategoriaDTO(obj));
		return listDTO;
	}
	
}
