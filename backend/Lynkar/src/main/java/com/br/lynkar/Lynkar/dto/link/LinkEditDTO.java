package com.br.lynkar.Lynkar.dto.link;

import java.util.UUID;

import lombok.Data;

@Data
public class LinkEditDTO {
	private UUID id;
	private String link;
	private String customCode;
	private boolean flActive;
}
