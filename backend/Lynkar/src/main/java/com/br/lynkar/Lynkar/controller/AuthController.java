package com.br.lynkar.Lynkar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.lynkar.Lynkar.dto.auth.AuthResponseDTO;
import com.br.lynkar.Lynkar.dto.auth.RefreshTokenDTO;
import com.br.lynkar.Lynkar.dto.common.ApiResponse;
import com.br.lynkar.Lynkar.dto.user.UserCreateDTO;
import com.br.lynkar.Lynkar.dto.user.UserLoginDTO;
import com.br.lynkar.Lynkar.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired 
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<Void>> register(@RequestBody UserCreateDTO dto) {
		authService.register(dto);
		return ResponseEntity.ok(ApiResponse.created());
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@RequestBody UserLoginDTO dto) {
		return ResponseEntity.ok(ApiResponse.ok(authService.login(dto)));
	}
	
	@PostMapping("/refreshToken")
	public ResponseEntity<ApiResponse<AuthResponseDTO>> refreshToken(@RequestBody RefreshTokenDTO dto) {
		return ResponseEntity.ok(ApiResponse.ok(authService.refreshToken(dto)));
	}

}
