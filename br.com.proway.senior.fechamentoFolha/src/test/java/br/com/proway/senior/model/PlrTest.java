package br.com.proway.senior.model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PlrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	@Test
	public void testPorcentagemPlr() throws Exception {
		Plr plr = new Plr();
		plr.setValorPorcentagemPlr(8);
		assertEquals(80.0, plr.getValorPorcentagemPlr(), 0.01);	
	}
}
