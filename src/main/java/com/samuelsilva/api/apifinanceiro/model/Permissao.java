package com.samuelsilva.api.apifinanceiro.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permissao")
@EqualsAndHashCode(of= {"codigo"})
public class Permissao {

	@Id
	@Getter @Setter
	private Long codigo;
	
	@Getter @Setter
	private String descricao;

}