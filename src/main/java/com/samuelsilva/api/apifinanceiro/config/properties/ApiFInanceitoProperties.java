package com.samuelsilva.api.apifinanceiro.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("apifinanceiro")
public class ApiFInanceitoProperties {

	@Getter @Setter
	private String originPermitida = "*";
	
	@Getter
	private final Seguranca seguranca = new Seguranca();
	
	public static class Seguranca{
		
		@Getter @Setter
		private boolean enableHttps;
		
	}	
	
}
