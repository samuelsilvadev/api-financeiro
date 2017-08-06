package com.samuelsilva.api.apifinanceiro.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findById(@PathVariable final Long id){
		 Categoria categoria = categoriaRepository.findOne(id);
		 return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.noContent().build();  
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Categoria categoria, HttpServletResponse response){
		Categoria newCat = categoriaRepository.save(categoria);

		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("/{id}")
					.buildAndExpand(newCat.getCodigo()).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(newCat);		
	}	
}