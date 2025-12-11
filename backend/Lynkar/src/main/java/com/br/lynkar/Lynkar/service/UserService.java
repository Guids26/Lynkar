package com.br.lynkar.Lynkar.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.lynkar.Lynkar.config.exception.LynkarBusinessException;
import com.br.lynkar.Lynkar.dto.user.UserAuthDataDTO;
import com.br.lynkar.Lynkar.dto.user.UserCreateDTO;
import com.br.lynkar.Lynkar.dto.user.UserEditDTO;
import com.br.lynkar.Lynkar.dto.user.UserResponseDTO;
import com.br.lynkar.Lynkar.mapper.UserMapper;
import com.br.lynkar.Lynkar.model.UserEntity;
import com.br.lynkar.Lynkar.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	public UserResponseDTO create(UserCreateDTO dto) {

		if (repository.existsByEmail(dto.getEmail())) {
			throw new LynkarBusinessException("Já existe um usuário com o email informado.");
		}
		LocalDateTime now = LocalDateTime.now();
		UserEntity userToCreate = mapper.dtoToEntity(dto);
		userToCreate.setActive(true);
		userToCreate.setCreatedAt(now);
		userToCreate.setUpdatedAt(now);
		userToCreate.setPassword(encoder.encode(dto.getPassword()));
		userToCreate = repository.save(userToCreate);

		return mapper.entityToDTO(userToCreate);
	}

	public Optional<UserResponseDTO> findById(UUID id) {
		Optional<UserEntity> userOptional = repository.findById(id);

		if (userOptional.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(mapper.entityToDTO(userOptional.get()));

	}

	public Optional<UserAuthDataDTO> getLoginDataByEmail(String email) {
		Optional<UserEntity> userOptional = repository.findByEmail(email);

		if (userOptional.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(mapper.entityToAuthDTO(userOptional.get()));
	}

	public Optional<UserAuthDataDTO> getLoginDataByRefreshToken(UUID refreshToken) {
		Optional<UserEntity> userOptional = repository.findFirstByRefreshToken(refreshToken);

		if (userOptional.isEmpty()) {
			return Optional.empty();
		}

		UserEntity user = userOptional.get();
		LocalDateTime now = LocalDateTime.now();

		if (now.isAfter(user.getRefreshTokenExpiresIn())) {
			return Optional.empty();
		}

		return Optional.of(mapper.entityToAuthDTO(user));
	}

	public void saveNewRefreshToken(UUID idUser, UUID refreshToken, LocalDateTime expiresIn) {
		Optional<UserEntity> userOptional = repository.findById(idUser);

		if (userOptional.isEmpty()) {
			return;
		}

		UserEntity user = userOptional.get();
		user.setRefreshToken(refreshToken);
		user.setUpdatedAt(LocalDateTime.now());
		user.setRefreshTokenExpiresIn(expiresIn);
		repository.save(user);
	}

	public UserResponseDTO update(UUID id, UserEditDTO dto) {

		UserEntity user = repository.findById(id)
				.orElseThrow(() -> new LynkarBusinessException("Usuário não encontrado com o id informado"));

		Optional<UserEntity> otherUserWithSameEmailOptional = repository.findByEmail(dto.getEmail());

		if (!otherUserWithSameEmailOptional.isEmpty() && !otherUserWithSameEmailOptional.get().getId().equals(id)) {
			throw new LynkarBusinessException("Já existe um usuário com o email informado");
		}

		LocalDateTime now = LocalDateTime.now();
		user.setUpdatedAt(now);
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());

		if (dto.getActualPassword() != null && !dto.getActualPassword().isEmpty() && dto.getNewPassword() != null
				&& !dto.getActualPassword().isEmpty()) {
			if (!encoder.matches(dto.getActualPassword(), user.getPassword())) {
				throw new LynkarBusinessException("A senha atual está incorreta");
			}

			user.setPassword(encoder.encode(dto.getNewPassword()));
		}
		
		user = repository.save(user);

		return mapper.entityToDTO(user);
	}
	
	public UserResponseDTO showProfile(UUID id) {
		return this.findById(id).orElseThrow(() -> new LynkarBusinessException("Usuário não encontrado na base."));
	}

}
