package com.br.lynkar.Lynkar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.lynkar.Lynkar.dto.common.ApiResponse;
import com.br.lynkar.Lynkar.dto.link.LinkCreateDTO;
import com.br.lynkar.Lynkar.dto.user.UserResponseDTO;
import com.br.lynkar.Lynkar.service.LinkService;

@RestController
@RequestMapping("/api/link")
public class LinkController {
	
	@Autowired
	private LinkService service;
	
	@PostMapping("")
	public ResponseEntity<ApiResponse<?>> create(@AuthenticationPrincipal UserResponseDTO user, @RequestBody LinkCreateDTO dto){
		return ResponseEntity.ok(ApiResponse.ok(service.create(dto, user.getId())));
	}

}
