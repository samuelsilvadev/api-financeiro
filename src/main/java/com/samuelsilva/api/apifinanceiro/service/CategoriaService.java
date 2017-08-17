package com.samuelsilva.api.apifinanceiro.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.samuelsilva.api.apifinanceiro.model.Categoria;
import com.samuelsilva.api.apifinanceiro.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository catRepository;
	
	public Categoria update(Long id, Categoria categoria) {
		Categoria categoriaSalva = findCatByCodigo(id);
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");		
		return catRepository.save(categoriaSalva); 
	}

	public Categoria findCatByCodigo(Long id) {
		Categoria pessoaSalva = catRepository.findOne(id);
		if(pessoaSalva == null)
			throw new EmptyResultDataAccessException(1);
		return pessoaSalva;
	}
}