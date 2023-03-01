package com.bernardpaula.LojaEletrodomesticos.repositories;

import com.bernardpaula.LojaEletrodomesticos.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
	
	@Query(value = "SELECT * FROM clientes C WHERE nome LIKE %:filtro%", nativeQuery=true)
	List<Cliente> filtrarPorNome(@Param("filtro")String filtro);
	
}
