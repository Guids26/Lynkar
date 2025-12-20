package com.br.lynkar.Lynkar.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lynkar.Lynkar.model.LinkVisitEntity;

public interface LinkVisitRepository extends JpaRepository<LinkVisitEntity, UUID>{
	
	Long countByLinkId(UUID linkId);

}
