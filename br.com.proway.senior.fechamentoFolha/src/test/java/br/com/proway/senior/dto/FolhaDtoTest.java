package br.com.proway.senior.dto;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.proway.senior.model.Folha;

public class FolhaDtoTest {

	
	@Test
	public void testGetValorHorasExtras() {
		Folha folha = new Folha();
		folha.getValorHorasExtras();
		FolhaDto folhaDto = new FolhaDto(folha);
		assertNotNull(folhaDto.getValorHorasExtras());
	}
	
	@Test
	public void testGetValorInss() {
		Folha folha = new Folha();
		folha.getValorInss();
		FolhaDto folhaDto = new FolhaDto(folha);
		assertNotNull(folhaDto.getValorInss());
	}
	
	@Test
	public void testGetValorImpostoDeRenda() {
		Folha folha = new Folha();
		folha.getValorImpostoDeRenda();
		FolhaDto folhaDto = new FolhaDto(folha);
		assertNotNull(folhaDto.getValorImpostoDeRenda());
	}
	
	@Test
	public void testGetValorPlanoSaude() {
		Folha folha = new Folha();
		folha.getValorPlanoSaude();
		FolhaDto folhaDto = new FolhaDto(folha);
		assertNotNull(folhaDto.getValorPlanoSaude());
	}
	
	@Test
	public void testGetValorValeTransporte() {
		Folha folha = new Folha();
		folha.getValorValeTransporte();
		FolhaDto folhaDto = new FolhaDto(folha);
		assertNotNull(folhaDto.getValorValeTransporte());
	}
	@Test
	public void testGetSalarioBruto() {
		Folha folha = new Folha();
		folha.getSalarioBruto();
		FolhaDto folhaDto = new FolhaDto(folha);
		assertNotNull(folhaDto.getSalarioBruto());
	}
	@Test
	public void testGetSalarioLiquido() {
		Folha folha = new Folha();
		folha.getSalarioLiquido();
		FolhaDto folhaDto = new FolhaDto(folha);
		assertNotNull(folhaDto.getSalarioLiquido());
	}
	@Test
	public void testGetValorFGTS() {
		Folha folha = new Folha();
		folha.getValorFGTS();
		FolhaDto folhaDto = new FolhaDto(folha);
		assertNotNull(folhaDto.getValorFGTS());
	}
}
