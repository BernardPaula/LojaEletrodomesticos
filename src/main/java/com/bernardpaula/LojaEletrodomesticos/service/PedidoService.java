package com.bernardpaula.LojaEletrodomesticos.service;

import com.bernardpaula.LojaEletrodomesticos.domain.*;
import com.bernardpaula.LojaEletrodomesticos.domain.enums.EstadoPagamento;
import com.bernardpaula.LojaEletrodomesticos.repositories.*;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.InformacoesItemPedidoDTO;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.InformacoesPedidoDTO;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.ItemPedidoDTO;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.PedidoSaveDTO;
import com.bernardpaula.LojaEletrodomesticos.service.exceptions.ObjectNotFoundException;
import com.bernardpaula.LojaEletrodomesticos.service.exceptions.PedidoNaoEncontradoException;
import com.bernardpaula.LojaEletrodomesticos.service.exceptions.RegraNegocioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

	private PedidoRepository pedidoRepo;
	private ClienteRepository cliRepo;
	private ProdutoRepository prodRepo;
	private PagamentoRepository pagRepo;
	private ItemPedidoRepository itemRepo;
	
	public PedidoService(PedidoRepository pedidoRepo, ClienteRepository cliRepo, ProdutoRepository prodRepo,
							PagamentoRepository pagRepo, ItemPedidoRepository itemRepo) {
		this.pedidoRepo = pedidoRepo;
		this.cliRepo = cliRepo;
		this.prodRepo = prodRepo;
		this.pagRepo = pagRepo;
		this.itemRepo = itemRepo;
	}
	
	@Transactional
	public Pedido save(PedidoSaveDTO pedDTO) {
		
		Cliente cliente = cliRepo.findById(pedDTO.getClienteId()).orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + pedDTO.getClienteId() + "Tipo: " + Cliente.class.getName()));
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		//pedido.setInstante(Date.from(Instant.now()));
		pedido.setDataPedido(LocalDate.now());
		
		Pagamento pag = new Pagamento(null, EstadoPagamento.toEnum(pedDTO.getEstadoPagamento()), pedido);
		
		pedido.setPagamento(pag);
		pedido.setTotal(pedDTO.getTotal());
		
		List<ItemPedido> itensPedidos = converterItensDoPedido(pedDTO.getItens(), pedido);
		pedido.setItems(itensPedidos);
	
		pedidoRepo.save(pedido);
		cliRepo.save(cliente);
		pagRepo.save(pag);
		itemRepo.saveAll(itensPedidos);
		
		return pedido;
		
	}
	
	public List<ItemPedido> converterItensDoPedido(List<ItemPedidoDTO> itens, Pedido pedido){
		
		if(itens.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem itens");
		}
		
		return itens.stream().map( item -> {
			
		Produto produto = prodRepo.findById(item.getProdutoId())
				.orElseThrow(() -> new ObjectNotFoundException("Código de produto inválido! Id: " + item.getProdutoId()));

		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setPedido(pedido);
		itemPedido.setDesconto(item.getDesconto());
		itemPedido.setPreco(item.getPreco());
		itemPedido.setQuantidade(item.getQuantidade());
		itemPedido.setProduto(produto);
		
		return itemPedido;
		
		}).collect(Collectors.toList());
	}
	
	
	public Pedido buscarPedidoCompleto(Integer id) {
		Optional<Pedido> pedido = pedidoRepo.fetchPedidoComItens(id);
		return pedido.orElseThrow(() -> new PedidoNaoEncontradoException());
	}
	
	
	public InformacoesPedidoDTO converterPedidoParaDTO(Pedido pedido) {
		
		return InformacoesPedidoDTO.builder()
				   .id(pedido.getId())
				   .nomeCliente(pedido.getCliente().getNome())
				   .cpfOuCnpj(pedido.getCliente().getCpfOuCnpj())
				   .estadoPagamento(pedido.getPagamento().getEstado().getDescricao())
				   .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				   .total(pedido.getTotal())
				   .itens(converterItens(pedido.getItems()))
				   .build();
								   
	}
	
	public List<InformacoesItemPedidoDTO> converterItens(List<ItemPedido> itens){
		
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map( item -> 
				
				InformacoesItemPedidoDTO.builder()
										.id(item.getId())
										.desconto(item.getDesconto())
										.preco(item.getPreco())
										.quantidade(item.getQuantidade())
										.build()	
		).collect(Collectors.toList());
	}
	
	
	@Transactional
	public void atualizarStatus(EstadoPagamento estado, Integer id) {
		pedidoRepo.findById(id).map( pedido -> {
			pedido.getPagamento().setEstado(estado);
			return pedidoRepo.save(pedido);
		}).orElseThrow(() -> new PedidoNaoEncontradoException());
		
		//return ped.getPagamento();
	}
	
	
	public void deletar(Integer id) {
		
		pedidoRepo.findById(id).map( pedido -> {
			pedidoRepo.deleteById(id); 
			return pedido;
		}).orElseThrow(() -> new PedidoNaoEncontradoException());
	
		
		
		//pedidoRepo.findById(id).map( pedido ->)
	}
	
}
