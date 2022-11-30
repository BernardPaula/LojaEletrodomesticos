package com.bernardpaula.lojaEletrodomesticos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardpaula.lojaEletrodomesticos.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
