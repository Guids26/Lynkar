package com.br.lynkar.Lynkar.config.exception;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Token de autenticação inválido";

	public AuthenticationException() {
		super(AuthenticationException.MESSAGE);
	}

}
