package com.derso.security.oauth2.compromissos;

import java.util.List;

import com.derso.security.oauth2.usuarios.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Compromisso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Usuario dono;
	
	@ManyToMany
	private List<Usuario> convidados; 

}
