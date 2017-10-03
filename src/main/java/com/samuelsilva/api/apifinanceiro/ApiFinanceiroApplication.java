package com.samuelsilva.api.apifinanceiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.samuelsilva.api.apifinanceiro.config.properties.ApiFInanceitoProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApiFInanceitoProperties.class)
public class ApiFinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiFinanceiroApplication.class, args);
	}
} 