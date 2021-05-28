package br.com.proway.senior.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import br.com.proway.senior.model.Plr;

public class PlrControllerTest {
	
	@Test
	public void testCadastrarPlr() throws Exception {
		PlrController pc =  new PlrController();
		pc.cadastrarPlr(LocalDate.now(), 350);
		assertTrue(pc.buscarTodosPlr().size() == 1);
	}
	
	@Test
	public void testCadastrarMaisDeUmPlr() throws Exception {
		PlrController pc =  new PlrController();
		pc.cadastrarPlr(LocalDate.of(2021, 05, 18), 350);
		pc.cadastrarPlr(LocalDate.of(2021, 06, 18), 350);
	}
	
	@Test(expected = Exception.class)
	public void testCadastrarPlrFalso() throws Exception{
		PlrController pc =  new PlrController();
		pc.cadastrarPlr(LocalDate.now(), 310);
		pc.cadastrarPlr(LocalDate.now(), 350);
	}
	
	@Test
	public void testAtualizarPlr() throws Exception {
		PlrController pc =  new PlrController();
		
		pc.cadastrarPlr(LocalDate.of(2021, 05, 18), 350);
		pc.cadastrarPlr(LocalDate.of(2021, 06, 18), 350);

		pc.atualizarPlr(LocalDate.now(), 370.0);
		assertTrue(pc.buscarTodosPlr().get(1).getValorPorcentagemPlr() == 370.0);
	}
	
	@Test(expected = Exception.class)
	public void testAtualizarPlrFalso() throws Exception {
		PlrController pc =  new PlrController();
		
		pc.cadastrarPlr(LocalDate.of(2021, 05, 18), 350);
		pc.cadastrarPlr(LocalDate.of(2021, 06, 18), 350);
		
		pc.atualizarPlr(LocalDate.of(2020,02,01), 750);
		
	}
	
	@Test
	public void testGetValorPlrMes() throws Exception {
		PlrController pc =  new PlrController();
		pc.cadastrarPlr(LocalDate.now(), 350);
		assertTrue(pc.getValorPlrMes(LocalDate.now()) == 350.00);
	}
	
	@Test
	public void testGetValorPlrMesSemPlrCadastradoRetornaZero() {
		PlrController pc =  new PlrController();
		assertTrue(pc.getValorPlrMes(LocalDate.of(2021, 02, 03)) == 0.0);
	}
	
	@Test
	public void testBuscarTodosPlr() throws Exception {
		PlrController pc = new PlrController();
		pc.cadastrarPlr(LocalDate.now(), 350);
		assertTrue(pc.buscarTodosPlr().size() == 1);
	}
}