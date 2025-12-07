package com.br.lynkar.Lynkar.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lynkar.Lynkar.model.UserEntity;
import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, UUID> {
	
	Optional<UserEntity> findByEmail(String email);
	boolean existsByEmail(String email);
	
	Optional<UserEntity> findFirstByRefreshToken(UUID refreshToken);

}
