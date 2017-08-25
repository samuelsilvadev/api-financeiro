package com.samuelsilva.api.apifinanceiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.samuelsilva.api.apifinanceiro.model.Lancamento;
import com.samuelsilva.api.apifinanceiro.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento save(Lancamento lancamento){
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
}