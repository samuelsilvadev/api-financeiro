package com.samuelsilva.api.apifinanceiro.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pessoa")
@EqualsAndHashCode(exclude = {"nome"})
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long codigo;
	
	@NotNull
	@Size(min = 3, max = 50)
	@Getter @Setter	
	private String nome;	
	
	@NotNull
	@Getter @Setter
	private boolean ativo;
	
	@Embedded
	@Getter @Setter
	private Endereco endereco;
	
	@JsonIgnore
	@Transient
	public boolean isInativo(){
		return !this.ativo;
	}	
}