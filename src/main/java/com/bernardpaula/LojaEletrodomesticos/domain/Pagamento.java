package com.bernardpaula.LojaEletrodomesticos.domain;

import com.bernardpaula.LojaEletrodomesticos.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagamentos")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pagamento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer estado;
	
	@JsonIgnore
	@OneToOne
	@MapsId
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = (estado == null) ? null : estado.getCod();
		this.pedido = pedido;
	}
	
	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}
	
	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}
	
	
}
