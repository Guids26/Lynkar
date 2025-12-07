package com.br.lynkar.Lynkar.config.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {

		SecurityScheme securityScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
				.bearerFormat("JWT").in(SecurityScheme.In.HEADER).name("Authorization");

		return new OpenAPI().info(new Info().title("Lynkar API").version("1.0.0"))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
				.components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("bearerAuth", securityScheme));
	}
}
