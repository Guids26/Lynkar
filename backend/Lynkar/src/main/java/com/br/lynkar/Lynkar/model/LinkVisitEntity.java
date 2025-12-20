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
@Table(name = "link_visit")
@Data
public class LinkVisitEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(nullable = false, updatable = false)
	private UUID id;
	
	@Column
	private String ipAddress;
	
	@Column
	private String referer;
	
	@Column(name = "user_agent")
	private String userAgent;
	
	@Column(name = "visited_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "id_link", nullable = false)
	private UUID linkId;
}
