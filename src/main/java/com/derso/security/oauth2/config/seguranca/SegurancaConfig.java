package com.derso.security.oauth2.config.seguranca;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SegurancaConfig {
	
	// Ref.: https://github.com/EdyKnopfler/spring-boot-udemy-spring-security/blob/main/src/main/java/com/derso/security/config/SegurancaConfig.java
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AntPathRequestMatcher[] caminhosPermitidos = Arrays.asList(
				antMatcher("/"),
				antMatcher("/home"),
				antMatcher("/usuarios"),
				antMatcher("/static/**"),
				antMatcher("/jquery*")
		).toArray(AntPathRequestMatcher[]::new);
		
		AntPathRequestMatcher caminhosApi = antMatcher("/api/**");
		
		return http
			.authorizeHttpRequests(
				autorizacao -> autorizacao
					.requestMatchers(caminhosPermitidos).permitAll()
					.anyRequest().authenticated()
			)
			.formLogin(Customizer.withDefaults())
			.csrf(
				csrf -> csrf
					.ignoringRequestMatchers(caminhosApi)
			)
			// Isto substitui a antiga API :)
			// Refs.: https://www.baeldung.com/spring-security-oauth
			// TODO subir um Keycloak e 
			
			// Resource Server: valida tokens ao disponibilizar os recursos
			// (codado "na mão" no projeto da Udemy)
			// https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html
			// https://www.baeldung.com/spring-security-oauth-jwt
			.oauth2ResourceServer(oauth2 -> oauth2.jwt())
			.build();
	}
	
}
