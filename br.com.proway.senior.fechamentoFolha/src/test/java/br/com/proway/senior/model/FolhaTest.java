package br.com.proway.senior.model;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class FolhaTest {

	@Test
	public void testGetters() {
		Folha folha = new Folha();
		assertTrue(folha.getDataEmissao()==null);
		assertTrue(folha.getFeriasLiquido() == 0.0);
		assertTrue(folha.getId() == 0);
		assertTrue(folha.getIdColaborador() == null);
		assertTrue(folha.getSalarioBruto() == 0.0);
		assertTrue(folha.getSalarioLiquido() == 0.0);
		assertTrue(folha.getValorFerias() == 0.0);
		assertTrue(folha.getValorFGTS() == 0.0);
		assertTrue(folha.getValorHorasExtras() == 0.0);
		assertTrue(folha.getValorHorasFaltas() == 0.0);
		assertTrue(folha.getValorHorasTrabalhadas() == 0.0);
		assertTrue(folha.getValorImpostoDeRenda() == 0.0);
		assertTrue(folha.getValorImpostoDeRendaFerias() == 0.0);
		assertTrue(folha.getValorInss() == 0.0);
		assertTrue(folha.getValorInssFerias() == 0.0);
		assertTrue(folha.getValorPlanoSaude() == 0.0);
		assertTrue(folha.getValorPlr() == 0.0);
		assertTrue(folha.getValorReflexoDSR() == 0.0);
		assertTrue(folha.getValorValeTransporte() == 0.0);
	}

	@Test
	public void testSetters() {
		Folha folha = new Folha();

		folha.setDataEmissao(LocalDate.now());
		folha.setValorFGTS(150.0);

		assertTrue(folha.getDataEmissao().equals(LocalDate.now()));
		assertTrue(folha.getFeriasLiquido() == 0.0);
		assertTrue(folha.getId() == 0);
		assertTrue(folha.getIdColaborador() == null);
		assertTrue(folha.getSalarioBruto() == 0.0);
		assertTrue(folha.getSalarioLiquido() == 0.0);
		assertTrue(folha.getValorFerias() == 0.0);
		assertTrue(folha.getValorFGTS() == 150.0);
		assertTrue(folha.getValorHorasExtras() == 0.0);
		assertTrue(folha.getValorHorasFaltas() == 0.0);
		assertTrue(folha.getValorHorasTrabalhadas() == 0.0);
		assertTrue(folha.getValorImpostoDeRenda() == 0.0);
		assertTrue(folha.getValorImpostoDeRendaFerias() == 0.0);
		assertTrue(folha.getValorInss() == 0.0);
		assertTrue(folha.getValorInssFerias() == 0.0);
		assertTrue(folha.getValorPlanoSaude() == 0.0);
		assertTrue(folha.getValorPlr() == 0.0);
		assertTrue(folha.getValorReflexoDSR() == 0.0);
		assertTrue(folha.getValorValeTransporte() == 0.0);
	}

	/*
	 * @Test public void testConstructor() { Folha folha = new Folha(1, 1,
	 * LocalDate.now(), 10.0, 10.0, 15.0, 13.0, 80.0, 35.0, 13.0, 13.0, 10.0, 10.0,
	 * 15.0, 15.0, 15.0, 10.0, 10.0, 10.0);
	 * 
	 * assertTrue(folha.getValorFerias() == 15.0); }
	 */
}
