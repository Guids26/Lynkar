package com.br.lynkar.Lynkar.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.lynkar.Lynkar.dto.common.ApiResponse;
import com.br.lynkar.Lynkar.dto.user.UserEditDTO;
import com.br.lynkar.Lynkar.dto.user.UserResponseDTO;
import com.br.lynkar.Lynkar.service.UserService;

@RequestMapping("api/user")
@RestController
public class UserController {
	@Autowired
	private UserService service;

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(@PathVariable UUID id, @RequestBody UserEditDTO userUpdate) {
		return ResponseEntity.ok(ApiResponse.ok(service.update(id, userUpdate)));
	}

	@GetMapping("/profile")
	public ResponseEntity<ApiResponse<UserResponseDTO>> showProfile(@AuthenticationPrincipal UserResponseDTO user) {
		return ResponseEntity.ok(ApiResponse.ok(service.showProfile(user.getId())));
	}
}
