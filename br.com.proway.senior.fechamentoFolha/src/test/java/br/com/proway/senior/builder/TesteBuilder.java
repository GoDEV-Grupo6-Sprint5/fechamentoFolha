package br.com.proway.senior.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import br.com.proway.senior.model.Bonificacao;
import br.com.proway.senior.model.Folha;
import br.com.proway.senior.model.FolhaBuilder;
import br.com.proway.senior.model.FolhaDirector;
import br.com.proway.senior.model.externo.CargoFolha;
import br.com.proway.senior.model.externo.ColaboradorFolha;
import br.com.proway.senior.model.externo.FeriasFolha;
import br.com.proway.senior.model.externo.PontoFolha;

public class TesteBuilder{
	
	
	@Test
	public void testFolhaNormal() {
		ColaboradorFolha colaborador = new ColaboradorFolha(1, true, 100, 25, 205);
		PontoFolha ponto = new PontoFolha(220, 0, 0);
		CargoFolha cargo = new CargoFolha(3500, 0);
		
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);
		
		FolhaBuilder folhaBuilder = new FolhaBuilder();	
		FolhaDirector director = new FolhaDirector(folhaBuilder);
		Folha folha = director.createFolhaNormal(colaborador, ponto, cargo, bonificacao);
		assertTrue(folha.getDataEmissao().equals(LocalDate.now()));
		assertTrue(0.0 == folha.getValorPlr());
	}
	
	@Test
	public void testFolhaFerias() {
		ColaboradorFolha colaborador = new ColaboradorFolha(2, true, 100, 25,205);
		CargoFolha cargo = new CargoFolha(3500, 20);
		FeriasFolha ferias = new FeriasFolha(15, 3);
		
		FolhaBuilder folhaBuilder = new FolhaBuilder();	
		FolhaDirector director = new FolhaDirector(folhaBuilder);
		director.createFolhaFerias(colaborador, cargo, ferias);
		Folha folha = folhaBuilder.build();
		System.err.println(folha.getFeriasLiquido());
		assertEquals(2592.79, folha.getFeriasLiquido(),0.1);
	}
	
	@Test
	public void testFolhaHibrida() {
		ColaboradorFolha colaborador = new ColaboradorFolha(3, true, 100, 25,205);
		PontoFolha ponto = new PontoFolha(220, 30, 45);
		CargoFolha cargo = new CargoFolha(2578, 10);
		FeriasFolha ferias = new FeriasFolha(20, 10);
		
		FolhaBuilder folhaBuilder = new FolhaBuilder();	
		FolhaDirector director = new FolhaDirector(folhaBuilder);
		director.createFolhaHibrida(colaborador, ponto, cargo, ferias);
		Folha folha = folhaBuilder.build();
		assertTrue(folha.getValorPlr() == 0.0);
	}
	
	@Test
	public void testFolhaNormalComDependente() {
		ColaboradorFolha colaborador = new ColaboradorFolha(5, false, 217, 127,205);
		colaborador.setNome("Lucas Walim");
		colaborador.setEmail("lucas@gmail.com");
		colaborador.addDependentes("Lucas Grijo");
		colaborador.addDependentes("Marcelo");
		
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);
		
		PontoFolha ponto = new PontoFolha(220, 17.33, 2.17);
		CargoFolha cargo = new CargoFolha(4600, 40);
		
		FolhaBuilder folhaBuilder = new FolhaBuilder();	
		FolhaDirector director = new FolhaDirector(folhaBuilder);
		Folha folha = director.createFolhaNormal(colaborador, ponto, cargo, bonificacao);
		System.out.println(folha.toString());
		assertEquals(4310.7299065454545, folha.getSalarioLiquido(), 0.01);
		assertTrue(folha.getValorPlr() == 0.0);
	}
	
	@Test
	public void testFolhaNormal2() {
		ColaboradorFolha colaborador = new ColaboradorFolha(5, false, 0, 0,205);
		PontoFolha ponto = new PontoFolha(220, 0, 0);
		CargoFolha cargo = new CargoFolha(4500, 0);
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);
		
		FolhaBuilder folhaBuilder = new FolhaBuilder();	
		FolhaDirector director = new FolhaDirector(folhaBuilder);
		director.createFolhaNormal(colaborador, ponto, cargo, bonificacao);
		Folha folha = folhaBuilder.build();
		
		assertEquals(3740.00, folha.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testGetSet() {
		// Teste inutil pra subir Coverage
		ColaboradorFolha colaborador = new ColaboradorFolha(6, false, 0, 0,205);
		colaborador.setNome("Joao");
		colaborador.setEmail("joao@gmail.com");
		colaborador.setId(6);
		colaborador.setValeTransporte(false);
		colaborador.setPlanoSaudeCooparticipacao(10);
		colaborador.setPlanoSaudeMensalidade(50);
		ArrayList<String> listaDependentes = new ArrayList<String>();
		listaDependentes.add("Filho");
		colaborador.setDependentes(listaDependentes);
		
		assertEquals("Joao", colaborador.getNome());
		assertEquals("joao@gmail.com", colaborador.getEmail());
		assertFalse(colaborador.getValeTransporte());
		assertTrue(colaborador.getId() == 6);
		assertTrue(colaborador.getPlanoSaudeCooparticipacao() + colaborador.getPlanoSaudeMensalidade() == 60);
		assertTrue(colaborador.getDependentes().get(0).equals("Filho"));
	}
	
	@Test
	public void verBonificacaoCargoTrabalhadorComInsalubridade() {
		CargoFolha cargo = new CargoFolha(1850, 40);
		ColaboradorFolha colaborador = new ColaboradorFolha(6, false, 0, 0,205);
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(6);
		PontoFolha ponto = new PontoFolha(220, 0, 0);
		
		FolhaBuilder folhaBuilder = new FolhaBuilder();	
		FolhaDirector director = new FolhaDirector(folhaBuilder);
		Folha folha = director.createFolhaNormal(colaborador, ponto, cargo, bonificacao);
		
		assertEquals(2290 + 111, folha.getSalarioBruto(), 0.1);
	}
}
