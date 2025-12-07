package com.br.lynkar.Lynkar.dto.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T>{
	private int status;
	private T data;
	private boolean sucess;
	private String message;
	private LocalDateTime timestamp;
	
	public static <T> ApiResponse<T> ok(T data) {
		return ApiResponse.<T>builder()
				.status(200)
				.timestamp(LocalDateTime.now())
				.sucess(true)
				.message(null)
				.data(data)
				.build();
	}
	
	public static <T> ApiResponse<T> created() {
		return ApiResponse.<T>builder()
				.status(201)
				.timestamp(LocalDateTime.now())
				.sucess(true)
				.message(null)
				.data(null)
				.build();
	}
	
	public static <T> ApiResponse<T> error(Exception e, HttpStatus status) {
		return ApiResponse.<T>builder()
				.status(status.value())
				.timestamp(LocalDateTime.now())
				.sucess(false)
				.message(e.getMessage())
				.data(null)
				.build();
	}


}
