package com.bernardpaula.lojaEletrodomesticos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bernardpaula.lojaEletrodomesticos.domain.Categoria;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.CategoriaDTO;
import com.bernardpaula.lojaEletrodomesticos.repositories.CategoriaRepository;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.DataIntegrityException;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

	private CategoriaRepository repo;
	
	public CategoriaService(CategoriaRepository repo) {
		this.repo = repo;
	}
	
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException
								("Não foi possível encontrar a categoria. Id:" + id + "Tipo: " + Categoria.class.getName()));
	}
	
	@Transactional
	public Categoria save(CategoriaDTO objDTO) {
		Categoria categoria = updateDTO(objDTO);
		return repo.save(categoria);
	}
	
	public Categoria updateDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	public Categoria update(Categoria obj, Integer id) {
		Categoria objBanco = find(id);
		obj.setId(objBanco.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não foi possível deletar categoria pois existem produtos associados");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public List<Categoria> filtrar(Categoria filtro){
		ExampleMatcher matcher = ExampleMatcher.matching()
												.withIgnoreCase()
												.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		return repo.findAll(example);
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	
	
}
