package com.samuelsilva.api.apifinanceiro.repository.lancamento;

import com.samuelsilva.api.apifinanceiro.model.Lancamento;

import java.util.List;

import com.samuelsilva.api.apifinanceiro.repository.filters.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filterBy(LancamentoFilter lancamentoFilter);

}