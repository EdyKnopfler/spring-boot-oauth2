package com.derso.security.oauth2.compromissos;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@Getter
	private Usuario dono;
	
	@ManyToMany
	@Getter
	private List<Usuario> convidados;
	
	@Deprecated
	Compromisso() {
	}
	
	public Compromisso(Usuario dono, List<Usuario> convidados) {
		this.dono = dono;
		this.convidados = convidados;
	}

}
