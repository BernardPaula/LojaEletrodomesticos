package com.bernardpaula.lojaEletrodomesticos.domain;

import com.bernardpaula.lojaEletrodomesticos.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagamentos")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pagamento {
	
	@Id
	private Integer id;
	
	@Column(name = "estado_pagamento")
	private Integer estado;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;

	public Pagamento(EstadoPagamento estado, Pedido pedido) {
		super();
		this.estado = (estado == null)? null : estado.getCod();
		this.pedido = pedido;
	}
	

}
