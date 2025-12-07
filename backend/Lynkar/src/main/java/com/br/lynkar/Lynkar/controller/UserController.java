package com.br.lynkar.Lynkar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/user")
@RestController
public class UserController {

	@GetMapping("/teste")
	public String teste() {
		return "teste";
	}
}
