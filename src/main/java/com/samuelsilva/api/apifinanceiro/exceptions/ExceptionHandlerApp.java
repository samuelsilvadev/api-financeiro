package com.samuelsilva.api.apifinanceiro.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Getter;

@ControllerAdvice
public class ExceptionHandlerApp extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource message;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemDoUsuario = message.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDoDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemDoUsuario, mensagemDoDev));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	private List<Erro>criarListaDeErros(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		bindingResult.getFieldErrors().forEach(field -> {
			String mensagemUsuario = message.getMessage(field, LocaleContextHolder.getLocale());
			String mensagemDoDev = field.toString();
			erros.add(new Erro(mensagemUsuario, mensagemDoDev));
		});
		return erros;
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class })
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String mensagemUsuario = message.getMessage("mensagem.nao-encontrado", null, LocaleContextHolder.getLocale());                                   
		String mensagemDoDev = ex.toString();        
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDoDev));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
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
