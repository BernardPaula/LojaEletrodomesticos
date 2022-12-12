package com.bernardpaula.lojaEletrodomesticos.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bernardpaula.lojaEletrodomesticos.domain.Pagamento;
import com.bernardpaula.lojaEletrodomesticos.domain.Pedido;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.AtualizarEstadoPagamentoDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.InformacoesPedidoDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.dto.PedidoDTO;
import com.bernardpaula.lojaEletrodomesticos.domain.enums.EstadoPagamento;
import com.bernardpaula.lojaEletrodomesticos.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService service;
	
	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido salvar(@RequestBody PedidoDTO dto) {
		Pedido pedido = service.salvar(dto);
		return pedido;
	}
	
	
	@GetMapping("/buscar/{id}")
	public InformacoesPedidoDTO buscar(@PathVariable Integer id) {
		Pedido pedido = service.buscarPedidoCompleto(id);
		InformacoesPedidoDTO dto = service.converterPedidoCompleto(pedido);
		return dto;
	} 
	
	@PatchMapping("/Atualizar/Status/{id}")
	public Pagamento atualizarPedido(@RequestBody AtualizarEstadoPagamentoDTO dto, @PathVariable Integer id) {
		Pagamento pag = service.atualizarEstadoPagamentoDoPedido(EstadoPagamento.toEnum(dto.getAtualizarPedido()), id);
		return pag;
	}
	
	
	//UpdateStatus

	
}
