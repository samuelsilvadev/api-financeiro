package com.samuelsilva.api.apifinanceiro.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuelsilva.api.apifinanceiro.model.Lancamento;
import com.samuelsilva.api.apifinanceiro.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LancamentoService lancamentoService;
		
	@GetMapping
	public ResponseEntity<?> findAll(){
		 List<Lancamento> listLancamento = lancamentoService.findAll();
		 return listLancamento != null && !listLancamento.isEmpty() ? 
				 ResponseEntity.ok(listLancamento) : ResponseEntity.noContent().build();  
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findById(@PathVariable final Long id){
		 return Optional.ofNullable(lancamentoService.findLancamentoByCodigo(id))
				 .map(c -> ResponseEntity.ok(c))
				 .orElseGet(() -> ResponseEntity.notFound().build());  
	}	
}