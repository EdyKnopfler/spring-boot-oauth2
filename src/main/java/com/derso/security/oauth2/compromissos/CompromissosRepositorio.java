package com.derso.security.oauth2.compromissos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompromissosRepositorio extends JpaRepository<Compromisso, Long> {

	@Query("SELECT comp FROM Compromisso comp WHERE comp.dono.id = :idUsuario")
	List<Compromisso> pertencentesAoUsuario(@Param("idUsuario") long idUsuario);
	
	@Query("SELECT comp FROM Compromisso comp JOIN comp.convidados conv WHERE conv.id = :idUsuario")
	List<Compromisso> ondeEhConvidado(@Param("idUsuario") long idUsuario);
	
}
