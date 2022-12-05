package com.bernardpaula.lojaEletrodomesticos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bernardpaula.lojaEletrodomesticos.domain.Cliente;
import com.bernardpaula.lojaEletrodomesticos.domain.ItemPedido;
import com.bernardpaula.lojaEletrodomesticos.domain.Pagamento;
import com.bernardpaula.lojaEletrodomesticos.domain.Pedido;
import com.bernardpaula.lojaEletrodomesticos.domain.Produto;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.ItemPedidoDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.PedidoCompletoDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.enums.EstadoPagamento;
import com.bernardpaula.lojaEletrodomesticos.repositories.ClienteRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.PedidoRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.ProdutoRepository;
import com.bernardpaula.lojaEletrodomesticos.service.exceptions.ObjectNotFoundException;
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
	
	
	public Pedido salvar(PedidoCompletoDTO dto) {
		
		Cliente cliente = clienteRepo.findById(dto.getClienteId())
									.orElseThrow(() -> new ObjectNotFoundException("Id do cliente inválido."));
		
		
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataPedido(LocalDate.now());
		pedido.setTotal(dto.getTotal());
		
		Pagamento pag = new Pagamento(EstadoPagamento.toEnum(dto.getEstado()), pedido);
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
	
}
