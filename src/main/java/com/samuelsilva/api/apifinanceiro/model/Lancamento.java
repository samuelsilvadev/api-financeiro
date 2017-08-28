package com.samuelsilva.api.apifinanceiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="lancamento")
@EqualsAndHashCode(of={"codigo"})
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long codigo;
	
	@NotNull
	@Getter @Setter	
	private String descricao;	
	
	@NotNull
	@Column(name="data_vencimento")
	@Getter @Setter
	private LocalDate dataVencimento;
	
	@Column(name="data_pagamento")
	@Getter @Setter
	private LocalDate dataPagamento;
	
	@NotNull
	@Getter @Setter
	private BigDecimal valor;
	
	@Getter @Setter
	private String observacao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private TipoLancamento tipo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="codigo_categoria")
	@Getter @Setter
	private Categoria categoria;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="codigo_pessoa")
	@Getter @Setter
	private Pessoa pessoa;	
}