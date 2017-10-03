package com.samuelsilva.api.apifinanceiro.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.samuelsilva.api.apifinanceiro.event.RecursoCriadoEvent;
import com.samuelsilva.api.apifinanceiro.model.Lancamento;
import com.samuelsilva.api.apifinanceiro.repository.filters.LancamentoFilter;
import com.samuelsilva.api.apifinanceiro.repository.projection.ResumoLancamento;
import com.samuelsilva.api.apifinanceiro.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LancamentoService lancamentoService;
			
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
	public ResponseEntity<?> save(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		Lancamento newLanc = lancamentoService.save(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, newLanc.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(newLanc);		
	}	
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO')")
	public void delete(@PathVariable final Long id){
		lancamentoService.delete(id) ;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO')")
	public ResponseEntity<?> findByFilters(LancamentoFilter lancamentoFilter, Pageable pageable){
		 Page<Lancamento> listLancamento = lancamentoService.findByManyFilters(lancamentoFilter, pageable);
		 return listLancamento != null ? 
				 ResponseEntity.ok(listLancamento) : ResponseEntity.noContent().build();  
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO')")
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoService.resume(lancamentoFilter, pageable);
	}	
	
	@GetMapping(value="/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO')")
	public ResponseEntity<?> findById(@PathVariable final Long id){
		 return Optional.ofNullable(lancamentoService.findLancamentoByCodigo(id))
				 .map(c -> ResponseEntity.ok(c))
				 .orElseGet(() -> ResponseEntity.notFound().build());  
	}	
}