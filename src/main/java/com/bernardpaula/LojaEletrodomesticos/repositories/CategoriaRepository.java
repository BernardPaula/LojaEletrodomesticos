package com.bernardpaula.LojaEletrodomesticos.repositories;

import com.bernardpaula.LojaEletrodomesticos.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	@Query(value = "SELECT * FROM categorias WHERE nomes LIKE %:filtro%", nativeQuery=true)
	List<Categoria> filtrar(@Param("filtro")String filtro);
	
}
