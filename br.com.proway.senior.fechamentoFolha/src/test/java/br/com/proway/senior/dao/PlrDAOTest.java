package br.com.proway.senior.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.proway.senior.model.Plr;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlrDAOTest {
	
	
	@Test
	public void testInsert() {
		PlrDAO plrDao = PlrDAO.getInstance(PostgresConnector.getSession());
		Plr plr1 = new Plr();
		Plr plr2 = new Plr();
		plr1.setVencimento(LocalDate.of(2021, 02, 10));
		plr2.setVencimento(LocalDate.of(2021, 01, 10));
		plr1.setValorPlr(1000);
		plr2.setValorPlr(1500);
		plrDao.insert(plr1);
		plrDao.insert(plr2);
		assertTrue(plrDao.getAll().size() == 2);
	}
	
	@Test
	public void testDelete() {
		PlrDAO plrDao = PlrDAO.getInstance(PostgresConnector.getSession());
		Plr plr1 = new Plr();
		Plr plr2 = new Plr();
		plr1.setVencimento(LocalDate.of(2021, 02, 10));
		plr2.setVencimento(LocalDate.of(2021, 01, 10));
		plr1.setValorPlr(2000);
		plr2.setValorPlr(3500);
		plrDao.insert(plr1);
		plrDao.insert(plr2);
		plrDao.delete(plr2);
		assertTrue(plrDao.getAll().size() == 1);
	}
	
	@Test
	public void testGetById() {
		PlrDAO plrDao = PlrDAO.getInstance(PostgresConnector.getSession());
		Plr plr1 = new Plr();
		Plr plr2 = new Plr();
		plr1.setVencimento(LocalDate.of(2021, 02, 10));
		plr2.setVencimento(LocalDate.of(2021, 01, 10));
		plr1.setValorPlr(1000);
		plr2.setValorPlr(1500);
		plrDao.insert(plr1);
		plrDao.insert(plr2);
		assertTrue (plrDao.getById(plrDao.getAll().get(1).getId()).getValorPlr() == 1500);
	}
	
	@Test
	public void testGetByDate() {
		PlrDAO plrDao = PlrDAO.getInstance(PostgresConnector.getSession());
		Plr plr1 = new Plr();
		Plr plr2 = new Plr();
		plr1.setVencimento(LocalDate.of(2021, 02, 10));
		plr2.setVencimento(LocalDate.of(2021, 01, 10));
		plr1.setValorPlr(1000);
		plr2.setValorPlr(1500);
		plrDao.insert(plr1);
		plrDao.insert(plr2);
		assertTrue(plrDao.getByDate(LocalDate.of(2021, 02, 10)).get(0).getValorPlr() == 1000);
	}
	
	@Test
	public void testGetAll() {
		PlrDAO plrDao = PlrDAO.getInstance(PostgresConnector.getSession());
		Plr plr1 = new Plr();
		Plr plr2 = new Plr();
		plr1.setVencimento(LocalDate.of(2021, 02, 5));
		plr2.setVencimento(LocalDate.of(2021, 01, 5));
		plr1.setValorPlr(1000);
		plr2.setValorPlr(1500);
		plrDao.insert(plr1);
		plrDao.insert(plr2);
		List<Plr> listaPlr = plrDao.getAll();
		assertEquals(listaPlr.size(), 2);
	}
	
	@Test
	public void testUpdate() {
		PlrDAO plrDao = PlrDAO.getInstance(PostgresConnector.getSession());
		Plr plr1 = new Plr();
		Plr plr2 = new Plr();
		plr1.setVencimento(LocalDate.of(2021, 02, 5));
		plr2.setVencimento(LocalDate.of(2021, 01, 5));
		plr1.setValorPlr(1000);
		plr2.setValorPlr(1500);
		plrDao.insert(plr1);
		System.out.println(plr1);
		plrDao.insert(plr2);
		System.out.println(plr2);
		
		Plr plr2Updated = plrDao.getById(plrDao.getAll().get(0).getId());
		plr2Updated.setValorPlr(1600);
		plrDao.update(plr2Updated);
		
		assertTrue(plrDao.getById(plrDao.getAll().get(1).getId()).getValorPlr() == 1600);
	}
}