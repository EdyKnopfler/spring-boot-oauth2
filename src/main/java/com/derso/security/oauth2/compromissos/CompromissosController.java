package com.derso.security.oauth2.compromissos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.derso.security.oauth2.config.seguranca.ResourceOwner;
import com.derso.security.oauth2.usuarios.Usuario;
import com.derso.security.oauth2.usuarios.UsuariosRepositorio;

@Controller
public class CompromissosController {
	
	@Autowired
	private UsuariosRepositorio usuariosRepositorio;

	// TODO fazer algo aqui hahaha
	
	private Usuario donoDosCompromissos() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ResourceOwner dono = (ResourceOwner) authentication.getPrincipal();
		return usuariosRepositorio.findById(dono.getId()).get();
	}
	
}
