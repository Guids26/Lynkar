package com.br.lynkar.Lynkar.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.lynkar.Lynkar.config.exception.AuthenticationException;
import com.br.lynkar.Lynkar.dto.user.UserResponseDTO;
import com.br.lynkar.Lynkar.service.JwtService;
import com.br.lynkar.Lynkar.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final PathMatcher pathmatch = new AntPathMatcher();

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		boolean isPublic = Arrays.asList(SecurityConstants.PUBLIC_URLS).stream()
				.anyMatch(uriPublic -> pathmatch.match(uriPublic, uri));

		if (isPublic) {
			filterChain.doFilter(request, response);
			return;
		}

		String authorization = request.getHeader("Authorization");

		if (authorization == null || authorization.isBlank()) {
			throw new AuthenticationException();
		}
		authorization = authorization.replace("Bearer ", "");

		String userId = jwtService.getSubject(authorization);

		UserResponseDTO user = userService.findById(UUID.fromString(userId)).orElseThrow(() -> {
			throw new AuthenticationException();
		});
		
		
		if (!user.isActive()) {
			throw new AuthenticationException();
		}
		
		UsernamePasswordAuthenticationToken authToken =
	            new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
	    SecurityContextHolder.getContext().setAuthentication(authToken);

		filterChain.doFilter(request, response);

	}

}
