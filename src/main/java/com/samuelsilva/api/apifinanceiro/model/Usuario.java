package com.samuelsilva.api.apifinanceiro.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@EqualsAndHashCode(of= {"codigo"})
public class Usuario {

	@Id
	@Getter @Setter
	private Long codigo;

	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "codigo_usuario")
		, inverseJoinColumns = @JoinColumn(name = "codigo_permissao"))
	@Getter @Setter
	private List<Permissao> permissoes;	
	
}
	