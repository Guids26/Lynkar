package com.br.lynkar.Lynkar.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lynkar.Lynkar.model.LinkEntity;

public interface LinkRepository extends JpaRepository<LinkEntity, UUID> {
	
	Optional<LinkEntity> findFirstByCode(String code);
	
	List<LinkEntity> findByCreatedBy(UUID createdBy);
	
	boolean existsByCode(String code);
	
}
