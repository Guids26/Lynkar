package com.br.lynkar.Lynkar.service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.lynkar.Lynkar.config.exception.AuthenticationException;
import com.br.lynkar.Lynkar.config.exception.LynkarBusinessException;
import com.br.lynkar.Lynkar.dto.auth.AuthResponseDTO;
import com.br.lynkar.Lynkar.dto.auth.RefreshTokenDTO;
import com.br.lynkar.Lynkar.dto.user.UserAuthDataDTO;
import com.br.lynkar.Lynkar.dto.user.UserCreateDTO;
import com.br.lynkar.Lynkar.dto.user.UserLoginDTO;
import com.br.lynkar.Lynkar.mapper.AuthMapper;

@Service
public class AuthService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthMapper authMapper;
	
	@Value(value = "${jwt.refreshToken.expiration}")
	private Duration refreshTokenDuration;
	
	public AuthResponseDTO refreshToken(RefreshTokenDTO dto) {
		if(dto == null || dto.getRefreshToken() == null || dto.getRefreshToken().isBlank()) {
			throw new LynkarBusinessException("Refresh token não informado.");
		}
		
		Optional<UserAuthDataDTO> userDataOptional = userService.getLoginDataByRefreshToken(UUID.fromString(dto.getRefreshToken()));
		
		if(userDataOptional.isEmpty()) {
			throw new AuthenticationException();
		}
		
		UserAuthDataDTO userData = userDataOptional.get();
		
		return generateLoginTokens(userData);
	}
	
	public void register(UserCreateDTO dto) {
		userService.create(dto);
	}
	
	public AuthResponseDTO login (UserLoginDTO dto) {
		Optional<UserAuthDataDTO> userOptional =  userService.getLoginDataByEmail(dto.getEmail());
		
		if(userOptional.isEmpty()) {
			throw new LynkarBusinessException("O email informado não existe");
		}
		
		UserAuthDataDTO userData = userOptional.get();
		
		if(!encoder.matches(dto.getPassword(), userData.getPasswordHash())) {
			throw new LynkarBusinessException("Senha inválida.");
		}
		
		return generateLoginTokens(userData);
		
	}
	
	private AuthResponseDTO generateLoginTokens(UserAuthDataDTO userData) {
		String jwtToken = jwtService.generateToken(userData);
		AuthResponseDTO response = authMapper.userDataToAuthResponse(userData);
		response.setAccessToken(jwtToken);
		response.setAccessTokenExpiresIn(jwtService.getExpirationMs(jwtToken));
		
		Instant expiresAt = Instant.now().plus(refreshTokenDuration);
	    LocalDateTime refreshTokenExpiresIn = LocalDateTime.ofInstant(expiresAt, ZoneId.systemDefault());
	    UUID refreshToken = UUID.randomUUID();
		userService.saveNewRefreshToken(userData.getId(), refreshToken, refreshTokenExpiresIn);
		response.setRefreshToken(refreshToken.toString());
		response.setRefreshTokenExpiresIn(expiresAt.toEpochMilli());
		return response;
	}

}
