package com.bernardpaula.lojaEletrodomesticos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardpaula.lojaEletrodomesticos.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
