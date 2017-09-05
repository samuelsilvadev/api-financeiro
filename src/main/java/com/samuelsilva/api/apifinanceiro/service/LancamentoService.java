package com.samuelsilva.api.apifinanceiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.samuelsilva.api.apifinanceiro.model.Lancamento;
import com.samuelsilva.api.apifinanceiro.model.Pessoa;
import com.samuelsilva.api.apifinanceiro.repository.LancamentoRepository;
import com.samuelsilva.api.apifinanceiro.repository.filters.LancamentoFilter;
import com.samuelsilva.api.apifinanceiro.service.exceptions.PessoaInexistenteException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	public Lancamento save(Lancamento lancamento){
		Pessoa pessoa = pessoaService.findPessoaByCodigo(lancamento.getPessoa().getCodigo());
		if(pessoa == null || pessoa.isInativo()){
			throw new PessoaInexistenteException();
		}
		Lancamento newlancamento = lancamentoRepository.save(lancamento);
		return newlancamento;		
	}	
	
	public Lancamento findLancamentoByCodigo(Long id) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);
		if(lancamentoSalvo == null)
			throw new EmptyResultDataAccessException(1);
		return lancamentoSalvo;
	}
	
	public List<Lancamento> findAll() {
		List<Lancamento> findAll = lancamentoRepository.findAll();
		if(findAll == null)
			throw new EmptyResultDataAccessException(1);
		return findAll;
	}
	
	public List<Lancamento> findByManyFilters(LancamentoFilter lancamentoFilter) {
		List<Lancamento> findAll = lancamentoRepository.filterBy(lancamentoFilter);
		if(findAll == null)
			throw new EmptyResultDataAccessException(1);
		return findAll;
	}
}