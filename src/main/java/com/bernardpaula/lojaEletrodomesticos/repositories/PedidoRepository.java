package com.bernardpaula.lojaEletrodomesticos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bernardpaula.lojaEletrodomesticos.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	//@Query("select p from Pedido p left join fetch p.itens where p.id = :id")
	
	@Query(value = "SELECT * FROM pedidos p left join item_pedidos i ON i.pedido_id = :id", nativeQuery = true)
	Optional<Pedido> findPedidofetchItens(@Param("id")Integer id);
	
	
}
