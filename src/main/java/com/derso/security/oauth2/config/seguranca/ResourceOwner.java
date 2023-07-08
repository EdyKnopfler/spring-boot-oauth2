package com.derso.security.oauth2.config.seguranca;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.derso.security.oauth2.usuarios.Usuario;

// Integração da classe de domínio Usuário com o Spring Security
// Dá para implementar umas lógicas interessantes :)

// TODO esta classe foi pensada para o fluxo Password Credentials
// em um setup com o Authorization Server nesta aplicação.
// Vamos seguir com o livro e entender o fluxo com JWT e Keycloak primeiro.

@SuppressWarnings("serial")
public class ResourceOwner implements UserDetails {
	
	private Usuario usuario;

	public ResourceOwner(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Long getId() {
		return usuario.getId();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
