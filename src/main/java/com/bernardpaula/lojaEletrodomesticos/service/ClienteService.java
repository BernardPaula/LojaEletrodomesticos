package com.bernardpaula.lojaEletrodomesticos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bernardpaula.lojaEletrodomesticos.domain.Cidade;
import com.bernardpaula.lojaEletrodomesticos.domain.Cliente;
import com.bernardpaula.lojaEletrodomesticos.domain.Endereco;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.ClienteCompletoDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.ClienteDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.enums.TipoCliente;
import com.bernardpaula.lojaEletrodomesticos.repositories.ClienteRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.EnderecoRepository;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.DataIntegrityException;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
	
	private ClienteRepository repo;
	private EnderecoRepository enderecoRepo;
	
	public ClienteService(ClienteRepository repo, EnderecoRepository enderecoRepo) {
		this.repo = repo;
		this.enderecoRepo = enderecoRepo;
	}
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Não foi possível encontrar cliente"));
	}

	@Transactional
	public Cliente save(Cliente cliente) {
		cliente.setId(null);
		repo.save(cliente);
		enderecoRepo.saveAll(cliente.getEnderecos());
		return cliente;
	}
	
	public Cliente update(Cliente cliente, Integer id) {
		Cliente cliBanco = find(id);
		cliente.setId(cliBanco.getId());
		return repo.save(cliente);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível deletar um cliente que possui pedidos");
		}
	}
	
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public List<Cliente> filtrar(String palavra){
		List<Cliente> list = repo.filtrar(palavra);
		return list;
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente convertDTO (ClienteCompletoDTO dto){
		Cliente cli = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipoCliente()));
		Cidade cid = new Cidade(dto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), 
																	dto.getBairro(), dto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(dto.getTelefone1());
		if(dto.getTelefone2() != null) {
			cli.getTelefones().add(dto.getTelefone2());
		}
		if(dto.getTelefone3() != null) {
			cli.getTelefones().add(dto.getTelefone3());
		}
		return cli;
	}
	
	public Cliente converterClienteDTO(ClienteDTO dto) {
		Cliente cliente = new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
		return cliente;
	}
	
}
