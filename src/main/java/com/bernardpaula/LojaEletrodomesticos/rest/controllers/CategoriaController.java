package com.bernardpaula.LojaEletrodomesticos.rest.controllers;

import com.bernardpaula.LojaEletrodomesticos.domain.Categoria;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.CategoriaDTO;
import com.bernardpaula.LojaEletrodomesticos.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
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
	
	@PostMapping("/inserir")
	@ResponseStatus(HttpStatus.CREATED)  //201
	public Categoria save(@RequestBody @Valid CategoriaDTO dto) {
		Categoria cat = service.save(dto);
		return cat;
	}
	
	@PutMapping("/atualizar/{id}")
	public Categoria atualizar(@RequestBody @Valid CategoriaDTO dto, @PathVariable Integer id) {
		Categoria categoria = service.converterDTO(dto); 
		Categoria cat = service.atualizar(categoria, id);
		return cat;
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		service.deletar(id);
	}
	
	@GetMapping("/listar")
	public List<CategoriaDTO> findAll(){
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}
	
	@GetMapping("/page")
	public Page<CategoriaDTO> findPage(
			@RequestParam(value = "page", defaultValue = "0")Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC")String direction){
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
		return listDTO;
	}
	
	@GetMapping("/filtrar/{filtro}")
	public List<CategoriaDTO> filtrar(@PathVariable String filtro){
		List<Categoria> list = service.filtrar(filtro);
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return listDTO;}
	
}
