package com.samuelsilva.api.apifinanceiro.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuelsilva.api.apifinanceiro.event.RecursoCriadoEvent;
import com.samuelsilva.api.apifinanceiro.model.Categoria;
import com.samuelsilva.api.apifinanceiro.repository.CategoriaRepository;
import com.samuelsilva.api.apifinanceiro.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
			
	@Autowired
	private ApplicationEventPublisher publisher;	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA')")
	public ResponseEntity<?> findAll(){
		 List<Categoria> listCat = categoriaRepository.findAll();
		 return !listCat.isEmpty() ? ResponseEntity.ok(listCat) : ResponseEntity.noContent().build();  
	}
	
	@GetMapping(value="/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA')")	
	public ResponseEntity<?> findById(@PathVariable final Long id){
		 return Optional.ofNullable(categoriaRepository.findOne(id))
				 .map(c -> ResponseEntity.ok(c))
				 .orElseGet(() -> ResponseEntity.notFound().build());  
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA')")
	public ResponseEntity<?> save(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
		Categoria newCat = categoriaRepository.save(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, newCat.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(newCat);		
	}	
	
	@PutMapping(value="/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Categoria categoria){
		Categoria newCat = categoriaService.update(id, categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(newCat);		
	}	
}