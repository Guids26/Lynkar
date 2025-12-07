package com.br.lynkar.Lynkar.dto.auth;

import lombok.Data;

@Data
public class AuthResponseDTO {
	private String id;
	private String name;
	private String email;
	private String accessToken;
	private String refreshToken;
	private long refreshTokenExpiresIn;
	private long accessTokenExpiresIn;

}
