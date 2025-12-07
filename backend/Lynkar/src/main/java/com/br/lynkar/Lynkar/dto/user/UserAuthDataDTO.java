package com.br.lynkar.Lynkar.dto.user;

import java.util.UUID;

import lombok.Data;

@Data
public class UserAuthDataDTO {
	private UUID id;
	private String name;
	private String email;
	private String passwordHash;
}
