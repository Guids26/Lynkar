package com.br.lynkar.Lynkar.dto.link;

import java.util.UUID;

import lombok.Data;

@Data
public class LinkResponseDTO {
	private UUID id;
	private String code;
	private String redirectTo;
}
