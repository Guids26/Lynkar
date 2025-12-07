package com.br.lynkar.Lynkar.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.br.lynkar.Lynkar.service.JwtService;
import com.br.lynkar.Lynkar.service.UserService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

	private final JwtService jwtService;
	private final UserService userService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests(auth -> {
			auth.requestMatchers(SecurityConstants.PUBLIC_URLS).permitAll().anyRequest().authenticated();
		}).addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter(jwtService, userService);
	}

}
