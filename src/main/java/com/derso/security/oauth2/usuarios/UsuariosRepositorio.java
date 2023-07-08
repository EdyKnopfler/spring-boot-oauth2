package com.derso.security.oauth2.usuarios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepositorio extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findOneByEmail(String email);

}
