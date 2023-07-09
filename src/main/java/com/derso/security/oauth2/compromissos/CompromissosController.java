package com.derso.security.oauth2.compromissos;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/compromissos")
public class CompromissosController {
	

	@GetMapping
	public String acessandoLogado() {
		return donoDosCompromissos();
	}
	
	private String donoDosCompromissos() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt principal = (Jwt) authentication.getPrincipal();
		return principal.getClaim("email") + " / " + authentication.getAuthorities();		
	}
	
}
