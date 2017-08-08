package com.samuelsilva.api.apifinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuelsilva.api.apifinanceiro.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
