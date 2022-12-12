package com.bernardpaula.lojaEletrodomesticos.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.bernardpaula.lojaEletrodomesticos.domain.Cliente;
import com.bernardpaula.lojaEletrodomesticos.domain.ItemPedido;
import com.bernardpaula.lojaEletrodomesticos.domain.Pagamento;
import com.bernardpaula.lojaEletrodomesticos.domain.Pedido;
import com.bernardpaula.lojaEletrodomesticos.domain.Produto;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.InformacoesItemPedidoDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.InformacoesPedidoDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.ItemPedidoDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.PedidoDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.enums.EstadoPagamento;
import com.bernardpaula.lojaEletrodomesticos.repositories.ClienteRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.PedidoRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.ProdutoRepository;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.ObjectNotFoundException;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.PedidoNaoEncontradoException;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.RegraNegocioException;

@Service
public class PedidoService {

	private PedidoRepository repo;
	private ClienteRepository clienteRepo;
	private ProdutoRepository produtoRepo;
	
	public PedidoService(PedidoRepository repo, ClienteRepository clienteRepo, ProdutoRepository produtoRepo) {
		this.repo = repo;
		this.clienteRepo = clienteRepo;
		this.produtoRepo = produtoRepo;
	}
	
	
	public Pedido salvar(PedidoDTO dto) {
		
		Cliente cliente = clienteRepo.findById(dto.getClienteId())
									.orElseThrow(() -> new ObjectNotFoundException("Id do cliente inválido."));
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataPedido(LocalDate.now());
		pedido.setTotal(dto.getTotal());
		
		Pagamento pag = new Pagamento(EstadoPagamento.toEnum(dto.getEstadoPagamento()), pedido);
		pedido.setPagamento(pag);
		
		
		List<ItemPedido> itensPedidos = converterItens(pedido, dto.getItensPedidos());
		pedido.setItensPedidos(itensPedidos);
		
		return pedido;
	}
	
	public List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
		if(itens.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar pedido sem itens");
		}
		
		return itens.stream().map( item -> {
			
			Produto produto = produtoRepo.findById(item.getProdutoId())
								.orElseThrow(() -> new ObjectNotFoundException("Código de produto inválido! " + item.getProdutoId()));
			
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setDesconto(item.getDesconto());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			itemPedido.setQuantidade(item.getQuantidade());
			itemPedido.setPreco(item.getPreco());
			return itemPedido;
			
		}).collect(Collectors.toList());
	}
	
	
	public Pedido buscarPedidoCompleto(Integer id) {
		Optional<Pedido> pedido = repo.findPedidofetchItens(id);
			return pedido.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
	
	}
	
	
	public InformacoesPedidoDTO converterPedidoCompleto(Pedido pedido) {
		return InformacoesPedidoDTO.builder()
									.nomeCliente(pedido.getCliente().getNome())
									.codigoPedido(pedido.getId())
									.cpfOuCnpj(pedido.getCliente().getCpfOuCnpj())
									.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
									.estadoPagamento(pedido.getPagamento().getEstado())
									.total(pedido.getTotal())
									.itensPedidos(converterItens(pedido.getItensPedidos()))
									.build();
	}
	
	private List<InformacoesItemPedidoDTO> converterItens(List<ItemPedido> itens) {
		
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map( item ->
				InformacoesItemPedidoDTO.builder()
										.desconto(item.getDesconto())
										.descricao(item.getProduto().getDescricao())
										.quantidade(item.getQuantidade())
										.precoUnitario(item.getPreco())
										.build()
				).collect(Collectors.toList());
	}
	
	
	public Pagamento atualizarEstadoPagamentoDoPedido(EstadoPagamento estado, Integer id) {
		Pedido pedido = repo.findById(id).map( ped -> {
			ped.setEstado(estado.getCod());
			repo.save(ped);
			return ped;
		}).orElseThrow(() -> new PedidoNaoEncontradoException());
		
		return pedido.getPagamento();
	}
}
