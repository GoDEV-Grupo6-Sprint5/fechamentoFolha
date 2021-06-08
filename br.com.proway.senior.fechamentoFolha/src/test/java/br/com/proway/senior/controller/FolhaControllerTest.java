package br.com.proway.senior.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import br.com.proway.senior.dao.FolhaDAO;
import br.com.proway.senior.dao.PostgresConnector;
import br.com.proway.senior.model.Bonificacao;
import br.com.proway.senior.model.Folha;
import br.com.proway.senior.model.externo.CargoFolha;
import br.com.proway.senior.model.externo.ColaboradorFolha;
import br.com.proway.senior.model.externo.FeriasFolha;
import br.com.proway.senior.model.externo.PontoFolha;


public class FolhaControllerTest {
	
//	@Before
//	public void limparTabela() {
//		FolhaDAO.getInstance(PostgresConnector.getSession()).limparTabela();
//	}
	
	@Test
	public void AtestConstruirCargoFolha() {
		CargoFolha cargo = new CargoFolha(3500, 0);
		FolhaController fc = new FolhaController();
			
		CargoFolha cargoFolha = fc.construirCargoFolha(cargo.getSalarioBase(), cargo.getPercentualInsalubridade()); 
		assertNotNull(cargoFolha);
	}
	
	@Test
	public void BtestConstruirFolhaNormal() {
		FolhaController fc = new FolhaController();
		
		CargoFolha cargo = new CargoFolha(3500, 0);
		CargoFolha cargoFolha = fc.construirCargoFolha(cargo.getSalarioBase(), cargo.getPercentualInsalubridade());
		
		ColaboradorFolha colaborador = new ColaboradorFolha(0, false, 0, 0, 0);
		
		PontoFolha ponto = new PontoFolha(0, 0, 0);
		
		
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);
		
		Folha folha = fc.construirFolhaNormal(colaborador, ponto, cargoFolha, bonificacao);
		
		assertNotNull(folha);
		assertTrue(folha.getId() == 0);
	}
	
	
	@Test
	public void CtestConstruirFolhaFerias() {
		FolhaController fc = new FolhaController();
		
		CargoFolha cargo = new CargoFolha(3500, 0);
		CargoFolha cargoFolha = fc.construirCargoFolha(cargo.getSalarioBase(), cargo.getPercentualInsalubridade());
		
		ColaboradorFolha colaborador = new ColaboradorFolha(0, false, 0, 0, 0);
		
		FeriasFolha ferias = new FeriasFolha(0, 0); 
		
		Folha folha = fc.construirFolhaFerias(colaborador, ferias, cargoFolha);
		
		assertNotNull(folha);
		assertTrue(folha.getValorHorasTrabalhadas() == 0);
	}
	
	@Test
	public void DtestConstruirFolhaHibrida() {
		FolhaController fc = new FolhaController();
		
		CargoFolha cargo = new CargoFolha(3500, 0);
		CargoFolha cargoFolha = fc.construirCargoFolha(cargo.getSalarioBase(), cargo.getPercentualInsalubridade());
		
		ColaboradorFolha colaborador = new ColaboradorFolha(0, false, 0, 0, 0);
		
		FeriasFolha ferias = new FeriasFolha(0, 0);
		
		PontoFolha ponto = new PontoFolha(220, 0, 0);
		
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);
		
		Folha folha = fc.construirFolhaHibrida(colaborador, ponto, cargoFolha, ferias);
		
		assertNotNull(folha);
		assertTrue(folha.getSalarioBruto() == 3500);
	} 
	
	@Test
	public void EtestSaveFolha() {
		FolhaController fc = new FolhaController();
		FolhaDAO db = FolhaDAO.getInstance(PostgresConnector.getSession());
		
		CargoFolha cargo = new CargoFolha(3500, 0);
		CargoFolha cargoFolha = fc.construirCargoFolha(cargo.getSalarioBase(), cargo.getPercentualInsalubridade());
		
		ColaboradorFolha colaborador = new ColaboradorFolha(0, false, 0, 0, 0);
		
		PontoFolha ponto = new PontoFolha(0, 0, 0);
		
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);
		
		Folha folha = fc.construirFolhaNormal(colaborador, ponto, cargoFolha, bonificacao);
		int bancoAntesDeSalvar=(db.getAll().size());
		fc.salvarFolha(folha);
		int bancoDepoisDeSalvar=(db.getAll().size());
		assertEquals(bancoAntesDeSalvar, bancoDepoisDeSalvar -1);
	}
	


	@Test
	public void FtestEditarFolha() throws Exception {
		
		FolhaController fc = new FolhaController();
		
		//verificar a necessidade desta informação...pois o controller já chama o DAO.
		//FolhaDAO db = FolhaDAO.getInstance(PostgresConnector.getSession());
		
		CargoFolha cargo = new CargoFolha(3500, 0);
		CargoFolha cargoFolha = fc.construirCargoFolha(cargo.getSalarioBase(), cargo.getPercentualInsalubridade());
		
		ColaboradorFolha colaborador = new ColaboradorFolha(0, false, 0, 0, 0);
		
		PontoFolha ponto = new PontoFolha(0, 0, 0);
		
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);
		
		Folha folha = fc.construirFolhaNormal(colaborador, ponto, cargoFolha, bonificacao);
		fc.salvarFolha(folha);
		folha.setDataEmissao(LocalDate.of(2021,05,01));
		
		boolean folhaAlterada = fc.editarFolha(folha);
		assertTrue(folhaAlterada);
		
		//fc.editarFolha(folha);
		//assertTrue(db.getAll().lastIndexOf(folha.getId());
	//assertTrue(db.getAll().get(1).getDataEmissao().equals(LocalDate.of(2021, 05, 9)));
		
	}
	
	@Test
	public void GtestDeletarFolha() {
		
		FolhaController fc = new FolhaController();
		FolhaDAO db = FolhaDAO.getInstance(PostgresConnector.getSession());
		
		CargoFolha cargo = new CargoFolha(3500, 0);
		CargoFolha cargoFolha = fc.construirCargoFolha(cargo.getSalarioBase(), cargo.getPercentualInsalubridade());
		
		ColaboradorFolha colaborador = new ColaboradorFolha(0, false, 0, 0, 0);
		
		PontoFolha ponto = new PontoFolha(0, 0, 0);
		
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);
		
		Folha folha = fc.construirFolhaNormal(colaborador, ponto, cargoFolha, bonificacao);
		Folha folha2 = fc.construirFolhaNormal(colaborador, ponto, cargoFolha, bonificacao);
		
		fc.salvarFolha(folha);
		fc.salvarFolha(folha2);
		int bancoAntesDeDeletar=(db.getAll().size());
		fc.deletarFolha(folha2);
		int bancoDepoisDeDeletar=(db.getAll().size());
		assertEquals(bancoAntesDeDeletar, bancoDepoisDeDeletar+1);
		
	}

	@Test
	public void HtestGetAll(){
		FolhaController fc = new FolhaController();
		FolhaDAO db = FolhaDAO.getInstance(PostgresConnector.getSession());

		CargoFolha cargo = new CargoFolha(9999, 0);
		CargoFolha cargoFolha = fc.construirCargoFolha(cargo.getSalarioBase(), cargo.getPercentualInsalubridade());

		ColaboradorFolha colaborador = new ColaboradorFolha(0, false, 0, 0, 0);

		PontoFolha ponto = new PontoFolha(0, 0, 0);

		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);

		Folha folha = fc.construirFolhaNormal(colaborador, ponto, cargoFolha, bonificacao);
		int tamanhoInicial = fc.getAll().size();
		fc.salvarFolha(folha);

		assertEquals(tamanhoInicial+1, fc.getAll().size());

	}

	@Test
	public void ItestGetById(){
		FolhaController fc = new FolhaController();
		FolhaDAO db = FolhaDAO.getInstance(PostgresConnector.getSession());
		CargoFolha cargo = new CargoFolha(888, 0);
		CargoFolha cargoFolha = fc.construirCargoFolha(cargo.getSalarioBase(), cargo.getPercentualInsalubridade());
		ColaboradorFolha colaborador = new ColaboradorFolha(0, false, 0, 0, 0);
		PontoFolha ponto = new PontoFolha(0, 0, 0);
		Bonificacao bonificacao = new Bonificacao();
		bonificacao.setPorcentagemBonificacaoColaborador(0);
		Folha folha = fc.construirFolhaNormal(colaborador, ponto, cargoFolha, bonificacao);
		fc.salvarFolha(folha);

		assertEquals(folha, fc.getById(4));

	}
	
}