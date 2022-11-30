package com.bernardpaula.lojaEletrodomesticos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardpaula.lojaEletrodomesticos.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
