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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.samuelsilva.api.apifinanceiro.event.RecursoCriadoEvent;
import com.samuelsilva.api.apifinanceiro.model.Pessoa;
import com.samuelsilva.api.apifinanceiro.repository.PessoaRepository;
import com.samuelsilva.api.apifinanceiro.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PessoaService pessoaService;
		
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
	public ResponseEntity<?> findAll(){
		 List<Pessoa> listPessoa = pessoaRepository.findAll();
		 return !listPessoa.isEmpty() ? ResponseEntity.ok(listPessoa) : ResponseEntity.noContent().build();  
	}
	
	@GetMapping(value="/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
	public ResponseEntity<?> findById(@PathVariable final Long id){
		 return Optional.ofNullable(pessoaService.findPessoaByCodigo(id))
				 .map(c -> ResponseEntity.ok(c))
				 .orElseGet(() -> ResponseEntity.notFound().build());  
	}
	
	@DeleteMapping(value="/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable final Long id){
		 pessoaRepository.delete(id) ;
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA')")
	public ResponseEntity<?> save(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		Pessoa newPessoa = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, newPessoa.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(newPessoa);		
	}	
	
	@PutMapping(value="/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa){
		Pessoa pessoaSalva = pessoaService.update(id, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping(value="/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropAtivo(@PathVariable Long id, @Valid @RequestBody Boolean ativo){
		pessoaService.updatePropAtivo(id, ativo);		
	}
}