package com.bernardpaula.LojaEletrodomesticos.service;

import com.bernardpaula.LojaEletrodomesticos.domain.Produto;
import com.bernardpaula.LojaEletrodomesticos.repositories.ProdutoRepository;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.ProdutoDTO;
import com.bernardpaula.LojaEletrodomesticos.service.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

	private ProdutoRepository produtoRepo;
	
	public ProdutoService(ProdutoRepository produtoRepo) {
		this.produtoRepo = produtoRepo;
	}
	
	
	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepo.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException
				("Objeto não encontrado! Id: " + id + " Tipo: " + Produto.class.getName()));
	}
	
	@Transactional
	public Produto save(Produto pro) {
		pro.setId(null);
		return produtoRepo.save(pro);
	}
	
	public Produto update(Produto pro, Integer id) {
		Produto proBanco = find(id);
		pro.setId(proBanco.getId());
		return produtoRepo.save(pro);
	}
	
	
	public void delete(Integer id) {
		find(id);
		produtoRepo.deleteById(id);		
	}
	
	public List<Produto> findAll(){
		return produtoRepo.findAll();
	}
	
	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return produtoRepo.findAll(pageRequest);
	}
	
	public List<Produto> filtrar(String filtro){
		List<Produto> list = produtoRepo.filtrar(filtro);
		return list;
	}
	
	public Produto convertDTO(ProdutoDTO dto) {
		return new Produto(null, dto.getNome(), dto.getDescricao(), dto.getPreco());
	}
	
}
