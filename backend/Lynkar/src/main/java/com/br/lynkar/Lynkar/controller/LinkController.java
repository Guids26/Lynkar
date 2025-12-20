package com.br.lynkar.Lynkar.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.lynkar.Lynkar.dto.common.ApiResponse;
import com.br.lynkar.Lynkar.dto.link.LinkCreateDTO;
import com.br.lynkar.Lynkar.dto.link.LinkEditDTO;
import com.br.lynkar.Lynkar.dto.link.LinkResponseDTO;
import com.br.lynkar.Lynkar.dto.user.UserResponseDTO;
import com.br.lynkar.Lynkar.service.LinkService;

@RestController
@RequestMapping("/api/link")
public class LinkController {

	@Autowired
	private LinkService service;

	@GetMapping("/code/{code}")
	public ResponseEntity<ApiResponse<LinkResponseDTO>> getByCode(@PathVariable String code) {
		return ResponseEntity.ok(ApiResponse.ok(service.findByCode(code)));
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<LinkResponseDTO>> create(@AuthenticationPrincipal UserResponseDTO user,
			@RequestBody LinkCreateDTO dto) {
		return ResponseEntity.ok(ApiResponse.ok(service.create(dto, user.getId())));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<LinkResponseDTO>> update(@RequestBody LinkEditDTO dto, @PathVariable UUID id) {
		dto.setId(id);
		return ResponseEntity.ok(ApiResponse.ok(service.update(dto)));
	}
	
	@GetMapping("/redirect/{code}")
	public ResponseEntity<?> visitLink(@PathVariable String code) throws Exception {
		return ResponseEntity.status(HttpStatus.FOUND).location(new URI(service.visit(code).getRedirectTo())).build();
	}

}
