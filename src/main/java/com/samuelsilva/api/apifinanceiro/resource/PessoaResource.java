package com.samuelsilva.api.apifinanceiro.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.samuelsilva.api.apifinanceiro.model.Pessoa;
import com.samuelsilva.api.apifinanceiro.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
		
	@GetMapping
	public ResponseEntity<?> findAll(){
		 List<Pessoa> listPessoa = pessoaRepository.findAll();
		 return !listPessoa.isEmpty() ? ResponseEntity.ok(listPessoa) : ResponseEntity.noContent().build();  
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findById(@PathVariable final Long id){
		 return Optional.ofNullable(pessoaRepository.findOne(id))
				 .map(c -> ResponseEntity.ok(c))
				 .orElseGet(() -> ResponseEntity.notFound().build());  
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		Pessoa newPessoa = pessoaRepository.save(pessoa);

		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("/{id}")
					.buildAndExpand(newPessoa.getCodigo()).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(newPessoa);		
	}	
}