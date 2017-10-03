package com.samuelsilva.api.apifinanceiro.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.samuelsilva.api.apifinanceiro.model.TipoLancamento;

import lombok.Getter;
import lombok.Setter;

public class ResumoLancamento {

	@Getter @Setter
	private Long codigo;

	@Getter @Setter	
	private String descricao;	
	
	@Getter @Setter
	private LocalDate dataVencimento;
	
	@Getter @Setter
	private LocalDate dataPagamento;
	
	@Getter @Setter
	private BigDecimal valor;
	
	@Getter @Setter
	private TipoLancamento tipo;
	
	@Getter @Setter
	private String categoria;
	
	@Getter @Setter
	private String pessoa;

	public ResumoLancamento(Long codigo, String descricao, LocalDate dataVencimento, LocalDate dataPagamento,
			BigDecimal valor,TipoLancamento tipo, String categoria, String pessoa) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valor = valor;
		
		this.tipo = tipo;
		this.categoria = categoria;
		this.pessoa = pessoa;
	}	
	
	
	
}
