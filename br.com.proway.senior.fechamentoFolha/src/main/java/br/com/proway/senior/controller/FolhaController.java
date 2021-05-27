package br.com.proway.senior.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import br.com.proway.senior.dao.FolhaDAO;
import br.com.proway.senior.dao.PostgresConnector;
import br.com.proway.senior.model.Bonificacao;
import br.com.proway.senior.model.Folha;
import br.com.proway.senior.model.FolhaBuilder;
import br.com.proway.senior.model.FolhaDirector;
import br.com.proway.senior.model.IPlr;
import br.com.proway.senior.model.Plr;
import br.com.proway.senior.model.externo.CargoFolha;
import br.com.proway.senior.model.externo.ColaboradorFolha;
import br.com.proway.senior.model.externo.FeriasFolha;
import br.com.proway.senior.model.externo.PontoFolha;
import br.com.proway.senior.model.externo.interfaces.ICargoFolha;
import br.com.proway.senior.model.externo.interfaces.IColaboradorFolha;
import br.com.proway.senior.model.externo.interfaces.IFeriasFolha;
import br.com.proway.senior.model.externo.interfaces.IPontoFolha;

/**
 * <h1>Guarda os metodos responsaveis por construir a {@link Folha}.</h1>
 * 
 * <p>
 * Recebe dados atraves do {@link FolhaBuilder}, trata estes dados e cria um
 * objeto especifico de {@link Folha}, no fim, o envia para banco de dados.
 * </p>
 * 
 * @author Sprint 6: David Willian <dwillian676@gmail.com;
 * @author Sprint 6: Guilherme Ezequiel <guilhermeezequieldasilva@gmail.com>;
 * @author Sprint 6: Jonata Caetano <jonatacaetano88@gmail.com>
 * @author Sprint 6: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
 * @author Sprint 6: Samuel Levi <samuel.levi@senior.com.br>
 * 
 * @see Folha
 * @see FolhaBuilder
 */

@Controller
public class FolhaController {

	/**
	 * <h1>Referente a {@link FolhaDAO}</h1>
	 * 
	 * <p>
	 * Instancia um objeto {@link FolhaDAO} em uma variavel.
	 * </p>
	 * 
	 * @see FolhaDAO
	 */
	private FolhaDAO folhaDao;

	/**
	 * <h1>Constroi o {@link CargoFolha}</h1>
	 * 
	 * <p>
	 * Recebe um salario base e um percentual insalubridade, constroi o
	 * {@link CargoFolha} com esses parametros e retorna um {@link CargoFolha}.
	 * </p>
	 * 
	 * @param salarioBase             double, referente ao salario base informado
	 *                                para o cargo.
	 * @param percentualInsalubridade double, referente ao percentual de
	 *                                insalubridade informado para o cargo.
	 * 
	 * @return {@link CargoFolha}.
	 * 
	 * @see CargoFolha
	 */
	public CargoFolha construirCargoFolha(double salarioBase, double percentualInsalubridade) {
		return new CargoFolha(salarioBase, percentualInsalubridade);
	}

	/**
	 * <h1>Constroi a FolhaNormal.</h1>
	 * 
	 * <p>
	 * Recebe um objeto {@link IColaboradorFolha}, {@link IPontoFolha},
	 * {@link ICargoFolha} e {@link Bonificacao}. Constroi um novo objeto
	 * {@link Folha} com os parametros informados.
	 * </p>
	 * 
	 * @param colaborador {@link IColaboradorFolha}, referente ao
	 *                    {ColaboradorFolha} informado;
	 * @param ponto       {@link IPontoFolha}, referente ao {PontoFolha}
	 *                    informado;
	 * @param cargo       {@link ICargoFolha}, referente ao {@link CargoFolha}
	 *                    informado;
	 * @param bonificacao {@link Bonificacao}, referente a {@link Bonificacao}
	 *                    informada.
	 * 
	 * @return {@link Folha}
	 * 
	 * @see IColaboradorFolha
	 * @see IPontoFolha
	 * @see ICargoFolha
	 * ColaboradorFolha
	 * PontoFolha
	 * @see CargoFolha
	 * @see Bonificacao
	 * @see Folha
	 */
	public Folha construirFolhaNormal(IColaboradorFolha colaborador, IPontoFolha ponto, ICargoFolha cargo,
			Bonificacao bonificacao) {
		FolhaBuilder folhaBuilder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(folhaBuilder);
		director.createFolhaNormal(colaborador, ponto, cargo, bonificacao);
		return folhaBuilder.build();
	}

	/**
	 * <h1>Constroi a FolhaFerias.</h1>
	 * 
	 * <p>
	 * Recebe um objeto {@link IColaboradorFolha}, {@link IFeriasFolha} e
	 * {@link ICargoFolha}. Constroi um novo objeto {@link Folha} com os parametros
	 * informados.
	 * </p>
	 * 
	 * @param colaborador {@link IColaboradorFolha}, referente ao
	 *                    { ColaboradorFolha} informado;
	 * @param ferias      {@link IFeriasFolha}, referente a {FeriasFolha}
	 *                    informada.
	 * @param cargo       {@link ICargoFolha}, referente ao {@link CargoFolha}
	 *                    informado;
	 * 
	 * @return {@link Folha}.
	 * 
	 * @see IColaboradorFolha
	 * @see IFeriasFolha
	 * @see ICargoFolha
	 * @see Folha
	 * ColaboradorFolha
	 * FeriasFolha
	 * @see CargoFolha
	 */
	public Folha construirFolhaFerias(IColaboradorFolha colaborador, IFeriasFolha ferias, ICargoFolha cargo) {
		FolhaBuilder folhaBuilder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(folhaBuilder);
		director.createFolhaFerias(colaborador, cargo, ferias);
		return folhaBuilder.build();
	}

	/**
	 * <h1>Constroi a FolhaHibrida.</h1>
	 * 
	 * <p>
	 * Recebe um objeto {@link IColaboradorFolha}, {@link IPontoFolha},
	 * {@link ICargoFolha}, {@link IFeriasFolha} e {@link IPlr}. Constroi um novo
	 * objeto {@link Folha} com os parametros informados.
	 * </p>
	 * 
	 * @param colaborador {@link IColaboradorFolha}, referente ao
	 *                    {ColaboradorFolha} informado;
	 * @param ponto       {@link IPontoFolha}, referente ao {PontoFolha}
	 *                    informado;
	 * @param cargo       {@link ICargoFolha}, referente ao {@link CargoFolha}
	 *                    informado;
	 * @param ferias      {@link IFeriasFolha}, referente a {FeriasFolha}
	 *                    informado;
	 *
	 * @return {@link Folha}.
	 * 
	 * @see IColaboradorFolha
	 * @see IPontoFolha
	 * @see ICargoFolha
	 * @see IFeriasFolha
	 * @see IPlr
	 * @see Folha
	 * ColaboradorFolha
	 * PontoFolha
	 * @see CargoFolha
	 * FeriasFolha
	 * Plr
	 */
	public Folha construirFolhaHibrida(IColaboradorFolha colaborador, IPontoFolha ponto, ICargoFolha cargo,
			IFeriasFolha ferias) {
		FolhaBuilder folhaBuilder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(folhaBuilder);
		director.createFolhaHibrida(colaborador, ponto, cargo, ferias);
		return folhaBuilder.build();
	}

	/**
	 * <h1>Salva a {@link Folha} no banco de dados.</h1>
	 * 
	 * <p>
	 * Recebe um objeto {@link Folha}, chama a instancia do {@link FolhaDAO} e
	 * insere o objeto no banco de dados.
	 * </p>
	 * 
	 * @param folha {@link Folha}, referente a {@link Folha} informada.
	 *
	 * @see Folha
	 * @see FolhaDAO
	 */
	public boolean salvarFolha(Folha folha) {
		try {
			folhaDao = FolhaDAO.getInstance(PostgresConnector.getSession());
			folhaDao.insert(folha);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * <h1>Atualiza uma {@link Folha} do banco de dados.</h1>
	 * 
	 * <p>
	 * Recebe um objeto {@link Folha} como parametro e realiza a atualizacao desse
	 * objeto ja existente no banco.
	 * </p>
	 *
	 * @param folha {@link Folha}, referente a {@link Folha} informada
	 * @see Folha
	 */
	public boolean editarFolha(Folha folha) {
		try {
			folhaDao = FolhaDAO.getInstance(PostgresConnector.getSession());
			folhaDao.update(folha);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * <h1>Deletar {@link Folha} do banco.</h1>
	 * 
	 * <p>
	 * O metodo recebe um objeto {@link Folha} existente no banco de dados, e deleta
	 * o mesmo.
	 * </p>
	 * 
	 * @param folha {@link Folha}, referente a {@link Folha} informada.
	 *
	 */
	public boolean deletarFolha(Folha folha) {
		try {
			folhaDao = FolhaDAO.getInstance(PostgresConnector.getSession());
			folhaDao.delete(folha);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * <h1>Retorna todos os objetos do banco de dados</h1>.
	 *
	 * <p>
	 * Faz a conexao com o banco, tras todos os bancos e os lista em um List.
	 * </p>
	 *
	 * @see Folha
	 */
	public List<Folha> getAll() {
		folhaDao = FolhaDAO.getInstance(PostgresConnector.getSession());
		return folhaDao.getAll();
	}

	/**
	 * <h1>Retorna um objeto Folha</h1>.
	 *
	 * <p>
	 * Faz a conexao com o banco, tras todos os bancos e os lista em um List.
	 * </p>
	 *
	 * @see Folha
	 */
	public Folha getById(int id) {
		folhaDao = FolhaDAO.getInstance(PostgresConnector.getSession());
		return folhaDao.getById(id);
	}
}