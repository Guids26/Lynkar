package com.br.lynkar.Lynkar.dto.user;

import lombok.Data;

@Data
public class UserEditDTO {
	private String email;
	private String name;
	private String actualPassword;
	private String newPassword;
}
