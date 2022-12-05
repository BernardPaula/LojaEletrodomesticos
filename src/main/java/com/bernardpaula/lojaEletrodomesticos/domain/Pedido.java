package com.bernardpaula.lojaEletrodomesticos.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDate dataPedido;
	
	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "pedido")
	private Pagamento pagamento;
	
	private BigDecimal total;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	//@ManyToOne
	//@JoinColumn(name = "endereco_entrega_id")
	//private Endereco enderecoDeEntrega;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedidos = new ArrayList<>();
	
}
