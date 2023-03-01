package com.bernardpaula.LojaEletrodomesticos.service;

import com.bernardpaula.LojaEletrodomesticos.domain.Categoria;
import com.bernardpaula.LojaEletrodomesticos.repositories.CategoriaRepository;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.CategoriaDTO;
import com.bernardpaula.LojaEletrodomesticos.service.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

	private CategoriaRepository repo;
	
	public CategoriaService(CategoriaRepository repo) {
		this.repo = repo;
	}
	
	
	public Categoria find(Integer id) {
		Optional<Categoria> categoria = repo.findById(id);
		
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Não foi possível encontrar a categoria! Id: " + id + " Tipo: " + Categoria.class.getName()));
	}
	
	@Transactional
	public Categoria save(CategoriaDTO dto) {
		 Categoria cat = converterDTO(dto);
		 return repo.save(cat);	 
	}
	
	
	public Categoria atualizar(Categoria cat, Integer id) {
		Categoria catBanco = find(id);
			cat.setId(catBanco.getId());
			return repo.save(cat);	
	}
	
	public Categoria converterDTO(CategoriaDTO dto) {
		return new Categoria(null, dto.getNome());
	}
	
	public void deletar(Integer id) {
		Categoria cat = find(id);
		repo.delete(cat);
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public List<Categoria> filtrar(String filtro){
		List<Categoria> list = repo.filtrar(filtro);
		return list;
	}
	
	
}
