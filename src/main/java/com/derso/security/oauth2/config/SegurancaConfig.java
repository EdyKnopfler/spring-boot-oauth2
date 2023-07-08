package com.derso.security.oauth2.config;

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
				.build();
	}

}
