package com.derso.security.oauth2.compromissos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.derso.security.oauth2.usuarios.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles({"test"})
public class CompromissosRepositorioTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CompromissosRepositorio repositorio;
	
	private Usuario jao;
	private Usuario ze;
	private Usuario tiana;
	
	private Compromisso cabeleireiro;
	private Compromisso niverDaTiana;
	private Compromisso fugidinha;
	
	@BeforeEach
	public void criarRegistros() {
		jao = new Usuario("Jão", "jao@borda.com", "123");
		ze = new Usuario("Zé", "ze@bar.com", "456");
		tiana = new Usuario("Tiana", "tiana@vendinha.com", "789");
		
		entityManager.persist(jao);
		entityManager.persist(ze);
		entityManager.persist(tiana);
		
		cabeleireiro = new Compromisso("Cabeleireiro", tiana);
		
		niverDaTiana = new Compromisso("Festinha", tiana);
		niverDaTiana.convidar(ze);
		niverDaTiana.convidar(jao);
		
		fugidinha = new Compromisso("Encontro às escondidas", jao);
		fugidinha.convidar(tiana);
		
		entityManager.persist(cabeleireiro);
		entityManager.persist(niverDaTiana);
		entityManager.persist(fugidinha);
		
		entityManager.flush();
	}
	
	@Test
	public void achaOsCompromissosDoDono() {
		List<Compromisso> daTiana = repositorio.pertencentesAoUsuario(tiana.getId());
		List<Compromisso> doJao = repositorio.pertencentesAoUsuario(jao.getId());
		List<Compromisso> doZe = repositorio.pertencentesAoUsuario(ze.getId());
		
		assertEquals(2, daTiana.size());
		assertEquals(cabeleireiro.getDescricao(), daTiana.get(0).getDescricao());
		assertEquals(niverDaTiana.getDescricao(), daTiana.get(1).getDescricao());
		
		assertEquals(1, doJao.size());
		assertEquals(fugidinha.getDescricao(), doJao.get(0).getDescricao());
		
		assertEquals(0, doZe.size());
	}
	
	@Test
	public void achaOsConvitesRecebidos() {
		List<Compromisso> daTiana = repositorio.ondeEhConvidado(tiana.getId());
		List<Compromisso> doJao = repositorio.ondeEhConvidado(jao.getId());
		List<Compromisso> doZe = repositorio.ondeEhConvidado(ze.getId());
		
		assertEquals(1, daTiana.size());
		assertEquals(fugidinha.getDescricao(), daTiana.get(0).getDescricao());
		
		assertEquals(1, doJao.size());
		assertEquals(niverDaTiana.getDescricao(), doJao.get(0).getDescricao());

		assertEquals(1, doZe.size());
		assertEquals(niverDaTiana.getDescricao(), doZe.get(0).getDescricao());
	}

}
