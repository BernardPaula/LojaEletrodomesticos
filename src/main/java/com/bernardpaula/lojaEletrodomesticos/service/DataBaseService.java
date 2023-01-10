package com.bernardpaula.lojaEletrodomesticos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernardpaula.lojaEletrodomesticos.domain.Categoria;
import com.bernardpaula.lojaEletrodomesticos.domain.Cidade;
import com.bernardpaula.lojaEletrodomesticos.domain.Cliente;
import com.bernardpaula.lojaEletrodomesticos.domain.Endereco;
import com.bernardpaula.lojaEletrodomesticos.domain.Estado;
import com.bernardpaula.lojaEletrodomesticos.domain.ItemPedido;
import com.bernardpaula.lojaEletrodomesticos.domain.Pagamento;
import com.bernardpaula.lojaEletrodomesticos.domain.PagamentoBoleto;
import com.bernardpaula.lojaEletrodomesticos.domain.PagamentoCartao;
import com.bernardpaula.lojaEletrodomesticos.domain.Pedido;
import com.bernardpaula.lojaEletrodomesticos.domain.Produto;
import com.bernardpaula.lojaEletrodomesticos.domain.enums.EstadoPagamento;
import com.bernardpaula.lojaEletrodomesticos.domain.enums.Perfil;
import com.bernardpaula.lojaEletrodomesticos.domain.enums.TipoCliente;
import com.bernardpaula.lojaEletrodomesticos.repositories.CategoriaRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.CidadeRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.ClienteRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.EnderecoRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.EstadoRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.ItemPedidoRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.PagamentoRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.PedidoRepository;
import com.bernardpaula.lojaEletrodomesticos.repositories.ProdutoRepository;

@Service
public class DataBaseService {
	

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	public ItemPedidoRepository itemPedidoRepository;
	
	
public void instantiateDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", "Dell", "Descrição", 2500.00);
		Produto p2 = new Produto(null, "Impressora", "Cad","Descrição", 800.00);
		Produto p3 = new Produto(null, "Mouse", "Eager", "Descrição",  80.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo" , est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
	
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "15739030005", TipoCliente.PESSOAFISICA); 
		cli1.getTelefones().addAll(Arrays.asList("125458665", "124322122"));
		
		Cliente cli2 = new Cliente(null, "Ana Costa", "anacosta@gmail.com", "77060057048", TipoCliente.PESSOAFISICA); 
		cli2.addPerfil(Perfil.ADMIN);
		cli2.getTelefones().addAll(Arrays.asList("99876543", "9987654"));
		
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220234", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "Rua Amarela", "152", "Sala 580", "Centro", "12321134", cli2, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("10/10/2017 19:35"), 400.00, cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("30/09/2017 10:32"), 30.5, cli2); 
		Pedido ped3 = new Pedido(null, sdf.parse("12/08/2017 10:40"), 300.2, cli2); 

		
		
		
		
		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 5);
		ped1.setPagamento(pagto1);
		
		
		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1)); 
		
		
		ItemPedido ip1 = new ItemPedido(null, 3, null, 2000.00, ped1, p1);
		ItemPedido ip2 = new ItemPedido(null, 2, 10.00, 100.10, ped1, p3);
		ItemPedido ip3 = new ItemPedido(null, 5, 20.00, 500.3, ped3, p3);
		ItemPedido ip4 = new ItemPedido(null, 10, null, 3000.00, ped3, p3);
		
		ped1.getItensPedidos().addAll(Arrays.asList(ip1, ip2));
		ped2.getItensPedidos().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
		
	}
	
}
