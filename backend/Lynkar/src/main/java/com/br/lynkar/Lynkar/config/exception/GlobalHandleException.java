package com.br.lynkar.Lynkar.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.lynkar.Lynkar.dto.common.ApiResponse;

@ControllerAdvice
public class GlobalHandleException {
	
	@ExceptionHandler(LynkarBusinessException.class)
	public <T> ResponseEntity<ApiResponse<T>> handleBusinessExceptions(Exception e){
		return ResponseEntity.badRequest().body(ApiResponse.error(e, HttpStatus.BAD_REQUEST));
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public <T> ResponseEntity<ApiResponse<T>> handleAuthenticationExceptions(Exception e){
		return ResponseEntity.badRequest().body(ApiResponse.error(e, HttpStatus.UNAUTHORIZED));
	}
	
	@ExceptionHandler(Exception.class)
	public <T> ResponseEntity<ApiResponse<T>> handleGenericInternalServerError(Exception e){
		return ResponseEntity.badRequest().body(ApiResponse.error(e, HttpStatus.INTERNAL_SERVER_ERROR));
	}

}
