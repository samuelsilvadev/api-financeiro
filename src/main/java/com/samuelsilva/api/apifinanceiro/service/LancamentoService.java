package com.samuelsilva.api.apifinanceiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.samuelsilva.api.apifinanceiro.model.Lancamento;
import com.samuelsilva.api.apifinanceiro.model.Pessoa;
import com.samuelsilva.api.apifinanceiro.repository.LancamentoRepository;
import com.samuelsilva.api.apifinanceiro.repository.filters.LancamentoFilter;
import com.samuelsilva.api.apifinanceiro.repository.projection.ResumoLancamento;
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
	
	public void delete(Long codigoDoLancamento){
		Lancamento lancamento = findLancamentoByCodigo(codigoDoLancamento);
		lancamentoRepository.delete(lancamento.getCodigo());		
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
	
	public Page<Lancamento> findByManyFilters(LancamentoFilter lancamentoFilter, Pageable pageable) {
		Page<Lancamento> findAll = lancamentoRepository.filterBy(lancamentoFilter, pageable);
		if(findAll == null)
			throw new EmptyResultDataAccessException(1);
		return findAll;
	}
	
	public Page<ResumoLancamento> resume(LancamentoFilter lancamentoFilter, Pageable pageable) {
		Page<ResumoLancamento> findAll = lancamentoRepository.resume(lancamentoFilter, pageable);
		if(findAll == null)
			throw new EmptyResultDataAccessException(1);
		return findAll;
	}
}