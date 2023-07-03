package com.derso.security.oauth2.compromissos;

import java.util.ArrayList;
import java.util.List;

import com.derso.security.oauth2.usuarios.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class Compromisso {
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Getter
	private String descricao;
	
	@ManyToOne
	@Getter
	private Usuario dono;
	
	@ManyToMany
	@Getter
	private List<Usuario> convidados = new ArrayList<>();
	
	@Deprecated
	Compromisso() {
	}
	
	public Compromisso(String descricao, Usuario dono) {
		this.descricao = descricao;
		this.dono = dono;
	}
	
	public void convidar(Usuario usuario) {
		this.convidados.add(usuario);
	}

}
