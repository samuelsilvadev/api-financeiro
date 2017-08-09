package com.samuelsilva.api.apifinanceiro.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuelsilva.api.apifinanceiro.event.RecursoCriadoEvent;
import com.samuelsilva.api.apifinanceiro.model.Categoria;
import com.samuelsilva.api.apifinanceiro.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
			
	@Autowired
	private ApplicationEventPublisher publisher;	
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		 List<Categoria> listCat = categoriaRepository.findAll();
		 return !listCat.isEmpty() ? ResponseEntity.ok(listCat) : ResponseEntity.noContent().build();  
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findById(@PathVariable final Long id){
		 return Optional.ofNullable(categoriaRepository.findOne(id))
				 .map(c -> ResponseEntity.ok(c))
				 .orElseGet(() -> ResponseEntity.notFound().build());  
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
		Categoria newCat = categoriaRepository.save(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, newCat.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(newCat);		
	}	
}