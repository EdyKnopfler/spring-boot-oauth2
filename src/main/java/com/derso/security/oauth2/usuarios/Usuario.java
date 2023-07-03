package com.derso.security.oauth2.usuarios;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Usuario {
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Getter
	private String nome;
	
	@Getter
	private String email;
	
	@Getter
	@JsonIgnore
	private String senhaCriptografada;
	
	// Somente para uso da JPA
	@Deprecated
	Usuario() {
	}
	
	public Usuario (String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senhaCriptografada = senha;  // TODO será tratado
	}

}
