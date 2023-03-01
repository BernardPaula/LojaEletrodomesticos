package com.bernardpaula.LojaEletrodomesticos.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/*
	@JsonFormat(pattern = "dd/MM/yyyy HH:dd")
	private Date instante;
	*/
	
	@Column(name="data_pedido")
	private LocalDate dataPedido;
	
	private Double total;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
	List<ItemPedido> items = new ArrayList<>();


	public Pedido(Integer id, LocalDate dataPedido, Double total, Pagamento pagamento, Cliente cliente) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.total = total;
		this.pagamento = pagamento;
		this.cliente = cliente;
	}
	
	
	
}
