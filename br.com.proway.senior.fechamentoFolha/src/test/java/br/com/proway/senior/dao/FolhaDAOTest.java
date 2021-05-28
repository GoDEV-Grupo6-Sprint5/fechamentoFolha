package br.com.proway.senior.dao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import br.com.proway.senior.model.Bonificacao;
import br.com.proway.senior.model.Folha;
import br.com.proway.senior.model.FolhaBuilder;
import br.com.proway.senior.model.FolhaDirector;
import br.com.proway.senior.model.externo.CargoFolha;
import br.com.proway.senior.model.externo.ColaboradorFolha;
import br.com.proway.senior.model.externo.PontoFolha;

public class FolhaDAOTest {


	@Test
	public void testInsert() {
		FolhaDAO folhaDAO = FolhaDAO.getInstance(PostgresConnector.getSession());
		ColaboradorFolha colab = new ColaboradorFolha(1, false, 100, 43, 205);
		PontoFolha ponto = new PontoFolha(220, 2, 1);
		CargoFolha cargo = new CargoFolha(1752, 20);
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(6);
		FolhaBuilder builder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(builder);

		Folha folha = director.createFolhaNormal(colab, ponto, cargo, bonificacao);
		System.out.println(folha.toString());
		folhaDAO.insert(folha);
		assertEquals(1, folhaDAO.getAll().size());
	}

	@Test 
	public void testGetById() {
		PostgresConnector.getSession().clear();
		FolhaDAO folhaDAO = FolhaDAO.getInstance(PostgresConnector.getSession());
		ColaboradorFolha colab = new ColaboradorFolha(1, false, 100, 43, 205);
		PontoFolha ponto = new PontoFolha(220, 2, 1);
		CargoFolha cargo = new CargoFolha(1752, 20);
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(6);
		FolhaBuilder builder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(builder);

		Folha folha = director.createFolhaNormal(colab, ponto, cargo, bonificacao);

		folhaDAO.insert(folha);
		System.out.println("\n\n ************* \n -> -> -> : " + folha.getId());
		assertEquals(1, 1);
	}

	@Test
	public void testDelete() {
		FolhaDAO folhaDAO = FolhaDAO.getInstance(PostgresConnector.getSession());
		ColaboradorFolha colab2 = new ColaboradorFolha(2, false, 100, 43, 205);
		ColaboradorFolha colab = new ColaboradorFolha(1, false, 100, 43, 205);
		PontoFolha ponto = new PontoFolha(220, 2, 1);
		CargoFolha cargo = new CargoFolha(1752, 20);
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(6);
		FolhaBuilder builder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(builder);

		
		Folha folha = director.createFolhaNormal(colab, ponto, cargo, bonificacao);
		Folha folha2 = director.createFolhaNormal(colab2, ponto, cargo, bonificacao);
		folhaDAO.insert(folha);
		folhaDAO.insert(folha2);
		System.out.println(folha.toString());

		folhaDAO.delete(folha);
		folhaDAO.delete(folha2);
		assertEquals(0, folhaDAO.getAll().size());
	}

	@Test
	public void testGetAll() {
		FolhaDAO folhaDAO = FolhaDAO.getInstance(PostgresConnector.getSession());
		ColaboradorFolha colab = new ColaboradorFolha(1, false, 100, 43, 205);
		PontoFolha ponto = new PontoFolha(220, 2, 1);
		CargoFolha cargo = new CargoFolha(1752, 20);
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(6);
		FolhaBuilder builder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(builder);

		Folha folha = director.createFolhaNormal(colab, ponto, cargo, bonificacao);

		folhaDAO.insert(folha);

		assertEquals(folhaDAO.getAll().size(), 1);
	}

	@Test
	public void testUpdate() {
		FolhaDAO folhaDAO = FolhaDAO.getInstance(PostgresConnector.getSession());
		ColaboradorFolha colab = new ColaboradorFolha(1, false, 100, 43, 205);
		PontoFolha ponto = new PontoFolha(220, 2, 1);
		CargoFolha cargo = new CargoFolha(1752, 20);
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(6);
		FolhaBuilder builder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(builder);

		Folha folha = director.createFolhaNormal(colab, ponto, cargo, bonificacao);
		folhaDAO.insert(folha);

		folha.setDataEmissao(LocalDate.of(1988, 02, 18));
		folhaDAO.update(folha);
		folha = folhaDAO.getById(folha.getId());

		assertEquals(LocalDate.of(1988, 02, 18), folha.getDataEmissao());
	}

	@Test
	public void testGetByDate() {
		FolhaDAO folhaDAO = FolhaDAO.getInstance(PostgresConnector.getSession());
		ColaboradorFolha colab = new ColaboradorFolha(9, false, 100, 43, 205);
		PontoFolha ponto = new PontoFolha(220, 2, 1);
		CargoFolha cargo = new CargoFolha(1752, 20);

		FolhaBuilder builder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(builder);

		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);

		Folha folha = director.createFolhaNormal(colab, ponto, cargo, bonificacao);
		folhaDAO.insert(folha);

		Folha folhaData = folhaDAO.getByDate(LocalDate.now()).get(folhaDAO.getByDate(LocalDate.now()).size() - 1);

		assertEquals(folhaData.getDataEmissao(), LocalDate.now());
	}

	@Test
	public void testGetDoubleByColumn() {
		FolhaDAO folhaDAO = FolhaDAO.getInstance(PostgresConnector.getSession());
		ColaboradorFolha colab = new ColaboradorFolha(9, false, 100, 43, 205);
		PontoFolha ponto = new PontoFolha(220, 2, 1);
		CargoFolha cargo = new CargoFolha(1752, 20);

		FolhaBuilder builder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(builder);

		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);

		Folha folha = director.createFolhaNormal(colab, ponto, cargo, bonificacao);
		folhaDAO.insert(folha);

		List<Folha> folhaValor = folhaDAO.getDoubleByColumn("valorFGTS", 159.6244363636364);
		assertEquals(folhaValor.get(0), folha);
	}

}