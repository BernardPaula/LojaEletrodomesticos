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

import com.bernardpaula.lojaEletrodomesticos.domain.Produto;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.ProdutoDTO;
import com.bernardpaula.lojaEletrodomesticos.repositories.ProdutoRepository;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.DataIntegrityException;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	private ProdutoRepository repo;
	
	public ProdutoService(ProdutoRepository repo) {
		this.repo = repo;
	}
	
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException
						("Não foi possível encontrar produto! Id: " + id + "Tipo: " + Produto.class.getName()));
	}
	
	
	@Transactional
	public Produto insert(Produto obj) {
		return repo.save(obj);
	}
	
	
	public Produto update(Produto obj, Integer id) {
		Produto objBanco = find(id);
		obj.setId(objBanco.getId());
		return repo.save(obj);
	}
	
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possível deletar o produto");
		}
	}
	
	
	public List<Produto> findAll(){
		return repo.findAll();
	}
	
	
	public List<Produto> filtrar(Produto obj){
		ExampleMatcher matcher = ExampleMatcher.matching()
												.withIgnoreCase()
												.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(obj ,matcher);
		return repo.findAll(example);
	}
	
	public Page<Produto> findPage(Integer nome, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(nome, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Produto convertProdutoDTO(ProdutoDTO objDto) {
		return new Produto(null, objDto.getNome(), objDto.getMarca(), objDto.getDescricao(), objDto.getPreco());
	}
	
}
