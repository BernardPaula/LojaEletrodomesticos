package com.bernardpaula.LojaEletrodomesticos.repositories;

import com.bernardpaula.LojaEletrodomesticos.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query(value = "SELECT * FROM produtos p WHERE nomes LIKE %:filtro%", nativeQuery=true)
	List<Produto> filtrar(@Param("filtro")String filtro);
	
}
