package com.samuelsilva.api.apifinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuelsilva.api.apifinanceiro.model.Lancamento;
import com.samuelsilva.api.apifinanceiro.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

}
