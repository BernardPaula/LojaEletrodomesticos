package com.bernardpaula.LojaEletrodomesticos.service;

import com.bernardpaula.LojaEletrodomesticos.domain.Cliente;
import com.bernardpaula.LojaEletrodomesticos.domain.Endereco;
import com.bernardpaula.LojaEletrodomesticos.domain.enums.TipoCliente;
import com.bernardpaula.LojaEletrodomesticos.repositories.ClienteRepository;
import com.bernardpaula.LojaEletrodomesticos.repositories.EnderecoRepository;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.ClienteDTO;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.ClienteInsertDTO;
import com.bernardpaula.LojaEletrodomesticos.service.exceptions.DataIntegrityException;
import com.bernardpaula.LojaEletrodomesticos.service.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

	
	private ClienteRepository clienteRepo;
	private EnderecoRepository endRepo;
	
	public ClienteService(ClienteRepository clienteRepo, EnderecoRepository endRepo) {
		this.clienteRepo = clienteRepo;
		this.endRepo = endRepo;
	}
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException
				("Objeto não encontrado, Id: " + id + " Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente save(Cliente cliente) {
		cliente.setId(null);
		clienteRepo.save(cliente);
		endRepo.saveAll(cliente.getEnderecos());
		return cliente;
	}
	
	
	public Cliente update(Cliente cli, Integer id) {
		Cliente cliBanco = find(id);
		cli.setId(cliBanco.getId());
		cliBanco = clienteRepo.save(cli);
		return cliBanco;	
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clienteRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar um cliente que possi pedidos");
		}
	}
	
	public List<Cliente> findAll(){
		return clienteRepo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepo.findAll(pageRequest);
	}
	
	public List<Cliente> filtrar(String filtro){
		List<Cliente> list = clienteRepo.filtrarPorNome(filtro);
		return list;
	}
	
	
	public Cliente updateClienteDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}
	
	
	public Cliente convertDTO(ClienteInsertDTO cliDTO) {
		Cliente cli = new Cliente(null, cliDTO.getNome(), cliDTO.getEmail(),cliDTO.getCpfOuCnpj() ,TipoCliente.toEnum(cliDTO.getTipo()));
		Endereco end = new Endereco(null, cliDTO.getRua(), cliDTO.getBairro(), cliDTO.getNumero(), 
									cliDTO.getCidade(), cliDTO.getEstado(), cli);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(cliDTO.getTelefone1());
		
		if(cliDTO.getTelefone2() != null) {
			cli.getTelefones().add(cliDTO.getTelefone2());
		}
		
		if(cliDTO.getTelefone3() != null) {
			cli.getTelefones().add(cliDTO.getTelefone3());
		}
		return cli;
	}
	
}
