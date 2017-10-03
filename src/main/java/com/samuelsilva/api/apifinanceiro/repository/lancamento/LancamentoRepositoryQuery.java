package com.samuelsilva.api.apifinanceiro.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.samuelsilva.api.apifinanceiro.model.Lancamento;
import com.samuelsilva.api.apifinanceiro.repository.filters.LancamentoFilter;
import com.samuelsilva.api.apifinanceiro.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
	
	public Page<Lancamento> filterBy(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resume(LancamentoFilter lancamentoFilter, Pageable pageable);
	
}