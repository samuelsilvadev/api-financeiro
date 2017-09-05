package com.samuelsilva.api.apifinanceiro.repository.filters;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

public class LancamentoFilter {

	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVencimentoDe;
	
	@Getter @Setter
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVencimentoAte;
	
}