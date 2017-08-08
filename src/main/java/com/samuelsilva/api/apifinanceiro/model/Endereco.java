package com.samuelsilva.api.apifinanceiro.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Embeddable
public class Endereco {
	
	@Size(min = 3, max = 30)
	@Getter @Setter
	private String logradouro ;
	
	@Size(min = 3, max = 30)
	@Getter @Setter
	private String numero ;
	
	@Size(min = 3, max = 30)
	@Getter @Setter
	private String complemento ;
	
	@Size(min = 3, max = 30)
	@Getter @Setter
	private String bairro;
	
	@Size(min = 3, max = 30)
	@Getter @Setter
	private String cep ;
	
	@Size(min = 3, max = 30)
	@Getter @Setter
	private String cidade;
	
	@Size(min = 3, max = 30)
	@Getter @Setter
	private String estado;
	
}