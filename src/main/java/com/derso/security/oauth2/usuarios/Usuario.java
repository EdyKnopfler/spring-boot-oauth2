package com.derso.security.oauth2.usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Entity
@ToString
public class Usuario {
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Getter
	private String nome;
	
	@Getter
	private String email;
	
	// Somente para uso da JPA
	@Deprecated
	Usuario() {
	}
	
	public Usuario (String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

}
