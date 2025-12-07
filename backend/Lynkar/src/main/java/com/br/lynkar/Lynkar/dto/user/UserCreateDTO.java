package com.br.lynkar.Lynkar.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserCreateDTO {
	private String name;
	private String email;
	private String password;
}
