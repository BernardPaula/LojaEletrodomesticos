package com.bernardpaula.lojaEletrodomesticos.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bernardpaula.lojaEletrodomesticos.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/*@Column(name = "data_pedido")
	private LocalDate dataPedido;   */
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:dd")
	private Date instante;
	
	@Column(name = "total")
	private Double total;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	
	//@ManyToOne
	//@JoinColumn(name = "endereco_entrega_id")
	//private Endereco enderecoDeEntrega;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedidos = new ArrayList<>();
	

	public Pedido(Integer id, Date instante, Double total, 
			Cliente cliente) {
		super();
		this.id = id;
		this.instante = instante;
		this.total = total;
		this.cliente = cliente;
	}
	
	
	
}
