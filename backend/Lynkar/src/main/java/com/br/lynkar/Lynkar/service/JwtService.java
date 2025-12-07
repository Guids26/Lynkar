package com.br.lynkar.Lynkar.service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.br.lynkar.Lynkar.config.exception.AuthenticationException;
import com.br.lynkar.Lynkar.dto.user.UserAuthDataDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value(value = "${jwt.secret}")
	private String secret;
	
	@Value(value = "${jwt.expiration}")
	private long expirationMs;
	
	private SecretKey getSecretKey() {
		byte [] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(UserAuthDataDTO userData) {
		Date dateExpiration = Date.from(Instant.now().plusMillis(expirationMs));
		return Jwts.builder()
			.subject(userData.getId().toString())
			.claim("email", userData.getEmail())
			.claim("name", userData.getName())
			.issuedAt(new Date())
			.expiration(dateExpiration)
			.signWith(getSecretKey(), Jwts.SIG.HS256)
			.compact();
	}
	
	public String getSubject(String token) {
		Claims claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
		Date now = new Date();
		
		if(now.after(claims.getExpiration())) {
			throw new AuthenticationException();
		}
		
		return claims.getSubject();
	}
	
	public long getExpirationMs(String token) {
		Claims claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
		Instant expirationIn = claims.getExpiration().toInstant();
		
		return expirationIn.toEpochMilli() - Instant.now().toEpochMilli();
	}

}
