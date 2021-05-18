package br.com.proway.senior.controller;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class PlrControllerTest {
	
	@Test
	public void testCadastrarPlr() throws Exception {
		PlrController pc =  new PlrController();
		pc.cadastrarPlr(LocalDate.now(), 350);
		assertTrue(pc.buscarTodosPlr().size() == 1);
	}
	
	@Test(expected = Exception.class)
	public void testCadastrarPlrFalso() throws Exception{
		PlrController pc =  new PlrController();
		pc.cadastrarPlr(LocalDate.now(), 350);
	}
	
	@Test
	public void testAtualizarPlr() throws Exception {
		PlrController pc =  new PlrController();
		pc.atualizarPlr(LocalDate.now(), 375.0);
		assertTrue(pc.buscarTodosPlr().get(0).getValorPlr() == 375.0);
	}
	
	@Test(expected = Exception.class)
	public void testAtualizarPlrFalso() throws Exception{
		PlrController pc =  new PlrController();
		pc.atualizarPlr(LocalDate.of(2020,02,01), 750);
	}
	
	@Test
	public void testGetValorPlrMes() {
		PlrController pc =  new PlrController();
		assertTrue(pc.getValorPlrMes(LocalDate.now()) == 375.00);
	}
	
	@Test
	public void testGetValorPlrMesSemPlrCadastradoRetornaZero() {
		PlrController pc =  new PlrController();
		assertTrue(pc.getValorPlrMes(LocalDate.of(2021, 02, 03)) == 0.0);
	}
	
	@Test
	public void testBuscarTodosPlr() throws Exception {
		PlrController pc = new PlrController();
		assertTrue(pc.buscarTodosPlr().size() == 1);
	}
}