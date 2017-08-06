package com.samuelsilva.api.apifinanceiro.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuelsilva.api.apifinanceiro.model.Categoria;
import com.samuelsilva.api.apifinanceiro.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
		
	@GetMapping
	public ResponseEntity<?> findAll(){
		 List<Categoria> listCat = categoriaRepository.findAll();
		 return !listCat.isEmpty() ? ResponseEntity.ok(listCat) : ResponseEntity.noContent().build();  
	}
	
}