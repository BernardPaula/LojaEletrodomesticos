package com.bernardpaula.lojaEletrodomesticos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardpaula.lojaEletrodomesticos.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
