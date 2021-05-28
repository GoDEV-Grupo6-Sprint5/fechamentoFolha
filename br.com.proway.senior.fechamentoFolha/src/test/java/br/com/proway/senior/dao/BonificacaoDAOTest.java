package br.com.proway.senior.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.proway.senior.model.Bonificacao;

public class BonificacaoDAOTest {


	@Test
	public void testAdicionarBonificacao() {
		BonificacaoDAO bonificacaoDAO = BonificacaoDAO.getInstance(PostgresConnector.getSession());
		Bonificacao bonificacao = new Bonificacao();
		bonificacaoDAO.insert(bonificacao);
		bonificacao.getPorcentagemBonificacaoColaborador();
		assertNotNull(bonificacao);
		System.out.println(bonificacao);
	}

	@Test
	public void testAdiconarBonificaçãoException() {
		BonificacaoDAO bonificacaoDAO = BonificacaoDAO.getInstance(PostgresConnector.getSession());
		Bonificacao bonificacao = new Bonificacao();
		PostgresConnector.getSession().close();
		assertFalse(bonificacaoDAO.insert(bonificacao));
		
	}

	@Test
	public void testDeleteBonificacao() {
		BonificacaoDAO bonificacaoDAO = BonificacaoDAO.getInstance(PostgresConnector.getSession());
		Bonificacao bonificacao = new Bonificacao();
		bonificacaoDAO.insert(bonificacao);
		int tamanhoInicial = bonificacaoDAO.getAll().size();
		bonificacaoDAO.delete(bonificacao);
		int tamanhoFinal = bonificacaoDAO.getAll().size();
		assertEquals(tamanhoFinal, tamanhoInicial - 1);
	}

	@Test
	public void testUptadeBonificacao() {
		BonificacaoDAO bonificacaoDAO = BonificacaoDAO.getInstance(PostgresConnector.getSession());
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(8);
		bonificacaoDAO.insert(bonificacao);
System.out.println(bonificacao.getPorcentagemBonificacaoColaborador());
		bonificacao.setPorcentagemBonificacaoColaborador(7);
		bonificacaoDAO.update(bonificacao);
		System.out.println(bonificacao.getPorcentagemBonificacaoColaborador());
		assertEquals(0.07, bonificacao.getPorcentagemBonificacaoColaborador(), 0.0);
	}
}
