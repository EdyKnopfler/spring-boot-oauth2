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
	
	private long idTiana;
	private long idJao;
	private long idZe;
	
	@BeforeEach
	public void criarRegistros() {
		Usuario jao = new Usuario("Jão", "jao@borda.com", "123");
		Usuario ze = new Usuario("Zé", "ze@bar.com", "456");
		Usuario tiana = new Usuario("Tiana", "tiana@vendinha.com", "789");
		
		entityManager.persist(jao);
		entityManager.persist(ze);
		entityManager.persist(tiana);
		
		Compromisso cabeleireiro = new Compromisso("Cabeleireiro", tiana);
		
		Compromisso niverDaTiana = new Compromisso("Festinha", tiana);
		niverDaTiana.convidar(ze);
		niverDaTiana.convidar(jao);
		
		Compromisso fugidinha = new Compromisso("Encontro às escondidas", jao);
		fugidinha.convidar(tiana);
		
		entityManager.persist(cabeleireiro);
		entityManager.persist(niverDaTiana);
		entityManager.persist(fugidinha);
		
		entityManager.flush();
		
		idTiana = tiana.getId();
		idJao = jao.getId();
		idZe = ze.getId();
	}
	
	@Test
	public void achaOsCompromissosDoDono() {
		List<Compromisso> daTiana = repositorio.pertencentesAoUsuario(idTiana);
		List<Compromisso> doJao = repositorio.pertencentesAoUsuario(idJao);
		List<Compromisso> doZe = repositorio.pertencentesAoUsuario(idZe);
		
		assertEquals(2, daTiana.size());
		assertEquals("Cabeleireiro", daTiana.get(0).getDescricao());
		assertEquals("Festinha", daTiana.get(1).getDescricao());
		
		assertEquals(1, doJao.size());
		assertEquals("Encontro às escondidas", doJao.get(0).getDescricao());
		
		assertEquals(0, doZe.size());
	}
	
	@Test
	public void achaOsConvitesRecebidos() {
		List<Compromisso> daTiana = repositorio.ondeEhConvidado(idTiana);
		List<Compromisso> doJao = repositorio.ondeEhConvidado(idJao);
		List<Compromisso> doZe = repositorio.ondeEhConvidado(idZe);
		
		assertEquals(1, daTiana.size());
		assertEquals("Encontro às escondidas", daTiana.get(0).getDescricao());
		
		assertEquals(1, doJao.size());
		assertEquals("Festinha", doJao.get(0).getDescricao());

		assertEquals(1, doZe.size());
		assertEquals("Festinha", doZe.get(0).getDescricao());
	}

}
