package br.com.proway.senior.controller;

import br.com.proway.senior.dao.FolhaDAO;
import br.com.proway.senior.dao.PostgresConnector;
import br.com.proway.senior.model.Bonificacao;
import br.com.proway.senior.model.Folha;
import br.com.proway.senior.model.FolhaBuilder;
import br.com.proway.senior.model.FolhaDirector;
import br.com.proway.senior.model.IPlr;
import br.com.proway.senior.model.externo.CargoFolha;
import br.com.proway.senior.model.externo.interfaces.ICargoFolha;
import br.com.proway.senior.model.externo.interfaces.IColaboradorFolha;
import br.com.proway.senior.model.externo.interfaces.IFeriasFolha;
import br.com.proway.senior.model.externo.interfaces.IPontoFolha;

/**
 * <h1>Guarda os metodos responsaveis por construir a {@link Folha}.</h1>
 * 
 * <p>Recebe dados atraves do {@link FolhaBuilder}, trata estes dados e cria um
 * objeto especifico de {@link Folha}, no fim, o envia para banco de dados.</p>
 * 
 * @author Sprint 4: Bruno Oliveira
 * @author Sprint 4: Lucas Grijo
 * @author Sprint 5: Leonardo Felipe Silva <felipeleao217@gmail.com>;
 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
 * 
 * @see Folha
 * @see FolhaBuilder
 */
public class FolhaController {

	/**
	 * <h1>Referente a {@link FolhaDAO}</h1>
	 * 
	 * @author Sprint 5: Leonardo Felipe Silva <felipeleao217@gmail.com>;
	 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
	 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
	 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
	 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
	 * 
	 * @see FolhaDAO
	 */
	private FolhaDAO folhaDao;

	/**
	 * <h1>Constroi o {@link CargoFolha}</h1>
	 * 
	 * <p>Recebe um salarioBase e um percentualInsalubridade, constroi o
	 * {@link CargoFolha} com esses parametros e retorna um {@link CargoFolha}.</p>
	 * 
	 * @param salarioBase double, referente ao salario base informado
	 * para o cargo.
	 * @param percentualInsalubridade double, referente ao percentual de
	 * insalubridade informado para o cargo.
	 * 
	 * @return {@link CargoFolha}.
	 * 
	 * @author Sprint 5: Leonardo Felipe Silva <felipeleao217@gmail.com>;
	 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
	 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
	 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
	 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
	 * 
	 * @see CargoFolha
	 */
	public CargoFolha construirCargoFolha(double salarioBase, double percentualInsalubridade) {
		return new CargoFolha(salarioBase, percentualInsalubridade);
	}

	/**
	 * <h1>Constroi a FolhaNormal.</h1>
	 * 
	 * <p>Recebe um objeto {@link IColaboradorFolha}, {@link IPontoFolha}, 
	 * {@link ICargoFolha}, {@link IFeriasFolha} e {@link IPlr}.
	 * Constroi um novo objeto {@link Folha} com os parametros
	 * informados.</p>
	 * 
	 * @param colaborador {@link IColaboradorFolha}, referente ao {@link ColaboradorFolha};
	 * @param ponto {@link IPontoFolha}, referente ao {@link PontoFolha};
	 * @param cargo {@link ICargoFolha}, referente ao {@link CargoFolha};
	 * @param plr {@link IPlr}, referente ao {@link Plr}.
	 * 
	 * @author Sprint 5: Leonardo Felipe Silva <felipeleao217@gmail.com>;
	 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
	 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
	 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
	 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
	 * 
	 * @see IColaboradorFolha
	 * @see IPontoFolha
	 * @see ICargoFolha
	 * @see IFeriasFolha
	 * @see IPlr
	 * @see Folha
	 */
	public Folha construirFolhaNormal(IColaboradorFolha colaborador, IPontoFolha ponto, ICargoFolha cargo, Bonificacao bonificacao) {
		FolhaBuilder folhaBuilder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(folhaBuilder);
		director.createFolhaNormal(colaborador, ponto, cargo, bonificacao);
		return folhaBuilder.build();
	}

	/**
	 * <h1>Constroi a FolhaFerias.</h1>
	 * 
	 * <p>Recebe um objeto {@link IColaboradorFolha}, {@link IFeriasFolha} e 
	 * {@link ICargoFolha}. Constroi um novo objeto {@link Folha}
	 * com os parametros informados.</p>
	 * 
	 * @param colaborador {@link IColaboradorFolha}, referente ao {@link ColaboradorFolha};
	 * @param cargo {@link ICargoFolha}, referente ao {@link CargoFolha};
	 * @param ferias {@link IFeriasFolha}, referente ao {@link FeriasFolha}.
	 * 
	 * @return {@link Folha}.
	 * 
	 * @author Sprint 5: Leonardo Felipe Silva <felipeleao217@gmail.com>;
	 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
	 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
	 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
	 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
	 * 
	 * @see IColaboradorFolha
	 * @see IFeriasFolha
	 * @see ICargoFolha
	 * @see Folha
	 * @see ColaboradorFolha
	 * @see CargoFolha
	 * @see FeriasFolha
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
	 * <p>Recebe um objeto {@link IColaboradorFolha}, {@link IPontoFolha}, 
	 * {@link ICargoFolha}, {@link IFeriasFolha} e {@link IPlr}.
	 * Constroi um novo objeto {@link Folha} com os parametros
	 * informados.</p>
	 * 
	 * @param colaborador {@link IColaboradorFolha}, referente ao 
	 * {@link ColaboradorFolha} informado;
	 * @param ponto {@link IPontoFolha}, referente ao {@link PontoFolha} informado;
	 * @param cargo {@link ICargoFolha}, referente ao {@link CargoFolha} informado;
	 * @param ferias {@link IFeriasFolha}, referente a {@link FeriasFolha} informado;
	 * @param plr {@link IPlr}, refernete ao {@link Plr}.
	 * 
	 * @return {@link Folha}.
	 * 
	 * @author Sprint 5: Leonardo Felipe Silva <felipeleao217@gmail.com>;
	 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
	 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
	 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
	 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
	 * 
	 * @see IColaboradorFolha
	 * @see IPontoFolha
	 * @see ICargoFolha
	 * @see IFeriasFolha
	 * @see IPlr
	 * @see Folha
	 * @see ColaboradorFolha
	 * @see PontoFolha
	 * @see CargoFolha
	 * @see FeriasFolha
	 * @see Plr
	 */
	public Folha construirFolhaHibrida(IColaboradorFolha colaborador, IPontoFolha ponto, ICargoFolha cargo,
			IFeriasFolha ferias, IPlr plr) {
		FolhaBuilder folhaBuilder = new FolhaBuilder();
		FolhaDirector director = new FolhaDirector(folhaBuilder);
		director.createFolhaHibrida(colaborador, ponto, cargo, ferias);
		return folhaBuilder.build();
	}

	/**
	 * <h1>Salva a {@link Folha} no banco de dados.</h1>
	 * 
	 * <p>Recebe um objeto {@link Folha}, chama a instancia do {@link FolhaDAO} e
	 * insere o objeto no banco de dados.</p>
	 * 
	 * @param folha {@link Folha}, referente a folha informada.
	 */
	public void salvarFolha(Folha folha) {
		folhaDao = FolhaDAO.getInstance(PostgresConnector.getSession());
		folhaDao.insert(folha);
	}

	/**
	 * Atualiza uma Folha no banco.
	 * 
	 * Realiza a atualizacao de um objeto {@link Folha} ja existente no banco.
	 *
	 * @param objeto {@link Folha}
	 */
	public void editarFolha(Folha folha) {
		folhaDao = FolhaDAO.getInstance(PostgresConnector.getSession());
		folhaDao.update(folha);
	}

	/**
	 * Deletar folha do banco.
	 * 
	 * O metodo recebe um objeto {@link Folha} existente no banco de dados, e deleta
	 * o mesmo.
	 * 
	 * @param objeto {@link Folha}.
	 */
	public void deletarFolha(Folha folha) {
		folhaDao = FolhaDAO.getInstance(PostgresConnector.getSession());
		folhaDao.delete(folha);
	}
}