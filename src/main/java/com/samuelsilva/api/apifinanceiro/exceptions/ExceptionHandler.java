package com.samuelsilva.api.apifinanceiro.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Getter;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource message;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemDoUsuario = message.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDoDev = ex.getCause().toString();
		
		return handleExceptionInternal(ex, new Erro(mensagemDoUsuario, mensagemDoDev), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	static class Erro{
		
		@Getter
		private String mensagemUsuario;
		
		@Getter
		private String mensagemDesenvolvedor;

		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
	}
}
