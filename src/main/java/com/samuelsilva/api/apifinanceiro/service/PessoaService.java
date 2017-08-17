package com.samuelsilva.api.apifinanceiro.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.samuelsilva.api.apifinanceiro.model.Pessoa;
import com.samuelsilva.api.apifinanceiro.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa update(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = findPessoaByCodigo(id);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");		
		return pessoaRepository.save(pessoaSalva); 
	}
	
	public void updatePropAtivo(Long id, Boolean ativo) {
		Pessoa pessoaSalva = findPessoaByCodigo(id);
		pessoaSalva.setAtivo(ativo);				
		pessoaRepository.save(pessoaSalva); 
	}

	public Pessoa findPessoaByCodigo(Long id) {
		Pessoa pessoaSalva = pessoaRepository.findOne(id);
		if(pessoaSalva == null)
			throw new EmptyResultDataAccessException(1);
		return pessoaSalva;
	}
}