package com.br.lynkar.Lynkar.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "link")
@Data
public class LinkEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(nullable = false, updatable = false)
	private UUID id;
	
	@Column(name = "redirect_to", nullable = false)
	private String redirectTo;
	
	@Column(nullable = false, unique = true)
	private String code;
	
	@Column(name = "is_active", nullable = false)
	private boolean active;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "created_by")
	private UUID createdBy;
}
