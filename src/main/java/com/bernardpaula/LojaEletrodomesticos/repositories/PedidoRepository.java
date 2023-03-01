package com.bernardpaula.LojaEletrodomesticos.repositories;

import com.bernardpaula.LojaEletrodomesticos.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	//@Query(value = "select p from pedidos p left join fetch p.items where p.id = :id")
	//@Query(value = "SELECT p.*, i.* FROM pedidos p LEFT JOIN item_pedidos i ON p.id = i.pedido_id WHERE p.id = :id", nativeQuery=true)
	@Query("select p from Pedido p left join fetch p.items where p.id = :id")
	Optional<Pedido> fetchPedidoComItens(@Param("id")Integer id);

}
