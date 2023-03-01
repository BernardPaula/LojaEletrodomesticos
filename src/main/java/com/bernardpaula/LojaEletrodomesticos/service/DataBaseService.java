package com.bernardpaula.LojaEletrodomesticos.service;

import com.bernardpaula.LojaEletrodomesticos.domain.*;
import com.bernardpaula.LojaEletrodomesticos.domain.enums.EstadoPagamento;
import com.bernardpaula.LojaEletrodomesticos.domain.enums.TipoCliente;
import com.bernardpaula.LojaEletrodomesticos.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DataBaseService {

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private ClienteRepository cliRepo;
	
	@Autowired
	private EnderecoRepository endRepo;
	
	@Autowired
	private PedidoRepository pedRepo;
	
	@Autowired
	private PagamentoRepository pagRepo;
	
	@Autowired
	private ItemPedidoRepository itemRepo;
	
	
	
	public void instantiateDatabase() {
		

		Categoria cat1 = new Categoria(null, "Computador");
		Categoria cat2 = new Categoria(null, "Cozinha");
		Categoria cat3 = new Categoria(null, "banheiro");
		Categoria cat4 = new Categoria(null, "esporte");
		Categoria cat5 = new Categoria(null, "Quarto");
		Categoria cat6 = new Categoria(null, "Brinquedos");
		Categoria cat7 = new Categoria(null, "Celulares");
		Categoria cat8 = new Categoria(null, "Impressoras");
		
		Produto p1 = new Produto(null, "Inspiron", "Descrição", 2000.02);
		Produto p2 = new Produto(null, "Mouse", "Descrição", 70.50);
		
		Produto p3 = new Produto(null, "Panela", "Descrição", 83.23);
		Produto p4 = new Produto(null, "Geladeira", "Descrição", 3000.00);
		
		Produto p5 = new Produto(null, "Chuveiro", "Descrição", 130.22);
		
		Produto p6 = new Produto(null, "Maquina de Lavar", "Descrição", 130.22);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p3, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		
		
		
		Cliente cli1 = new Cliente(null, "João","joao@gmail.com", "27861070085", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Ana Júlia", "anajulia@hotmail.com", "84689569000104", TipoCliente.PESSOAJURIDICA);
		Cliente cli3 = new Cliente(null, "Samanta", "samanta@gmail.com", "61499314043", TipoCliente.PESSOAFISICA);
		Cliente cli4 = new Cliente(null, "Veder","veder@gmail.com", "27861070085", TipoCliente.PESSOAFISICA);
		Cliente cli5 = new Cliente(null, "Paulo","paulo@gmail.com", "22376940010", TipoCliente.PESSOAFISICA);
		Cliente cli6 = new Cliente(null, "Flávia", "flavia@hotmail.com", "56279424000191", TipoCliente.PESSOAJURIDICA);
		
		Endereco end1 = new Endereco(null, "Rua Amarela", "Centro", 677, "Santa Rosa", "Minas Gerais", cli1);
		Endereco end2 = new Endereco(null, "Rua Verde", "Tuiuiu", 654, "São Paulo", "São Paulo", cli2);
		Endereco end3 = new Endereco(null, "Rua das Margaridas", "Santana", 234, "Salvador", "Bahia", cli3);
		Endereco end4 = new Endereco(null, "Rua das Jacas", "Centro", 123, "Varginha", "Minas Gerais", cli4);
		Endereco end5 = new Endereco(null, "Rua Rosa", "Lapa", 232, "Cunha", "Bahia", cli5);
		Endereco end6 = new Endereco(null, "Rua 13 de Agosto", "Santana", 234, "AAA", "São Paulo", cli6);


		cli1.getEnderecos().addAll(Arrays.asList(end1));
		cli2.getEnderecos().addAll(Arrays.asList(end2));
		cli3.getEnderecos().addAll(Arrays.asList(end3));
		cli4.getEnderecos().addAll(Arrays.asList(end4));
		cli5.getEnderecos().addAll(Arrays.asList(end5));
		cli6.getEnderecos().addAll(Arrays.asList(end6));
		
		cliRepo.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6));
		endRepo.saveAll(Arrays.asList(end1, end2, end3, end4, end5, end6));
		
		
		
		
		Pedido ped1 = new Pedido(null, LocalDate.of(2010, 3, 7), 340.00, null, cli3);
		Pedido ped2 = new Pedido(null, LocalDate.of(2023, 01, 12), 500.10, null, cli1);
		
		Pagamento pag1 = new Pagamento(null, EstadoPagamento.PENDENTE, ped1);
		ped1.setPagamento(pag1);
		Pagamento pag2 = new Pagamento(null, EstadoPagamento.CANCELADO, ped2);
		ped2.setPagamento(pag2);
		
		Pagamento pag3 = new PagamentoBoleto(null, EstadoPagamento.QUITADO, ped2, null, null);
		
		ItemPedido item1 = new ItemPedido(null, 5, 530.00, 00.00, p1, ped1);
		ItemPedido item2 = new ItemPedido(null, 2, 100.30, 10.00, p2, ped2);
		
		ped1.getItems().addAll(Arrays.asList(item1));
		ped2.getItems().addAll(Arrays.asList(item2));
		
		
		pedRepo.saveAll(Arrays.asList(ped1, ped2));
		pagRepo.saveAll(Arrays.asList(pag1, pag2));
		itemRepo.saveAll(Arrays.asList(item1, item2));
		
		
		
	}
}
