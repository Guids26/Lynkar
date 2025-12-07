package com.br.lynkar.Lynkar.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class UserResponseDTO {
	private UUID id;
	private String email;
	private boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
