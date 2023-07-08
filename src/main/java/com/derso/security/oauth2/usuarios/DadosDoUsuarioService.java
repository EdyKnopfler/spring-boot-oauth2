package com.derso.security.oauth2.usuarios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.derso.security.oauth2.config.seguranca.ResourceOwner;

@Service
public class DadosDoUsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuariosRepositorio repositorio;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repositorio.findOneByEmail(email);
		return new ResourceOwner(usuario.orElseThrow(
				() -> new UsernameNotFoundException(email + " não foi encontrado")));
	}

}
