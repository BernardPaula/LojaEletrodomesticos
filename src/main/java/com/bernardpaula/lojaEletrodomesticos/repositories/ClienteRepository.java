package com.bernardpaula.lojaEletrodomesticos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardpaula.lojaEletrodomesticos.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
