package com.bernardpaula.lojaEletrodomesticos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bernardpaula.lojaEletrodomesticos.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Query(value = "SELECT * FROM clientes WHERE nome LIKE %:palavra%", nativeQuery = true)
	List<Cliente> filtrar(@Param("palavra")String palavra);
	
}
