package com.bernardpaula.LojaEletrodomesticos.rest.controllers;

import com.bernardpaula.LojaEletrodomesticos.domain.Pedido;
import com.bernardpaula.LojaEletrodomesticos.domain.enums.EstadoPagamento;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.AtualizarStatusPagamentoDTO;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.InformacoesPedidoDTO;
import com.bernardpaula.LojaEletrodomesticos.rest.controllers.dtos.PedidoSaveDTO;
import com.bernardpaula.LojaEletrodomesticos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

	private PedidoService service;
	
	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody @Valid PedidoSaveDTO pedidoDTO) {
		Pedido pedido = service.save(pedidoDTO);
		return pedido.getId();
	}
	
	@GetMapping("/buscar/{id}")
	public InformacoesPedidoDTO buscarPedidoCompleto(@PathVariable Integer id) {
		Pedido pedido = service.buscarPedidoCompleto(id);
		InformacoesPedidoDTO dto = service.converterPedidoParaDTO(pedido);
		return dto;
	}
	
	@PatchMapping("/atualizar/status/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarStatusPedido(@RequestBody AtualizarStatusPagamentoDTO statusDTO, @PathVariable Integer id) {
		 service.atualizarStatus(EstadoPagamento.toEnum(statusDTO.getStatusPagamento()), id);
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPedido(@PathVariable Integer id) {
		service.deletar(id);
	}
}
