package br.com.proway.senior.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.proway.senior.model.Bonificacao;
import br.com.proway.senior.model.Folha;
import org.springframework.stereotype.Repository;

/**
 * <h1>Reponsavel pelos comandos de DML.</h1>
 *
 * <p>
 * Possue os metodos CRUD da {@link Folha}.
 * </p>
 *
 * @author Sprint 6: David Willian <dwillian676@gmail.com>;
 * @author Sprint 6: Guilherme Ezequiel <guilhermeezequieldasilva@gmail.com>;
 * @author Sprint 6: Jonata Caetano <jonatacaetano88@gmail.com>
 * @author Sprint 6: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
 * @author Sprint 6: Samuel Levi <samuel.levi@senior.com.br>
 */
@Repository
public final class FolhaDAO implements InterfaceDAO<Folha> {
	/**
	 * <h1>Instancia da classe FolhaDAO em Singleton</h1>
	 */
	private static FolhaDAO instance;
	/**
	 * <h1>Session para as transacoes</h1>
	 */
	private Session session;

	/**
	 * <h1>Construtor privado para setar a sessao</h1>
	 *
	 * <p>
	 * Recebe uma session e atrubiu essa sessao a session da classe
	 * </p>
	 *
	 * @param session Session, referente a session informada.
	 *
	 */
	private FolhaDAO(Session session) {
		this.session = session;
	}

	/**
	 * <h1>Cria uma instancia caso n�o exista ou usa a existente.</h1>
	 *
	 * <p>
	 * Recebe uma session. Verifica a existencia de uma instancia, caso j� exista
	 * ele atribui a instancia uma sessao da {@link FolhaDAO} caso n�o exista ele
	 * cria uma nova instancia.
	 * </p>
	 *
	 * @param session Session, referente a session informada.
	 * @return instance {@link FolhaDAO#instance}, referente a sessao da
	 *         {@link FolhaDAO}
	 * @see FolhaDAO
	 * @see FolhaDAO#instance
	 */
	public static FolhaDAO getInstance(Session session) {
		if (instance == null) {
			instance = new FolhaDAO(session);
		}
		return instance;
	}

	/**
	 * <h1>Cria e retorna uma nova instancia.</h1>
	 *
	 * <p>
	 * Recebe uma session e cria uma nova instance da {@link FolhaDAO}
	 * </p>
	 *
	 * @see FolhaDAO
	 */
	public static FolhaDAO newInstance(Session session) {
		instance = new FolhaDAO(session);
		return instance;
	}

	/**
	 * <h1>Insere uma {@link Folha} no banco de dados.</h1>
	 *
	 * <p>
	 * Recebe um objeto {@link Folha}, abre uma session e insere o objeto no banco
	 * de dados.
	 * </p>
	 *
	 * @param folhaASerInserida {@link Folha}, referente a {@link Folha}
	 * @see Folha
	 */
	public boolean insert(Folha folhaASerInserida) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		try {
			session.save(folhaASerInserida);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * <h1>Deleta um objeto {@link Folha} do banco de dados.</h1>
	 *
	 * <p>
	 * Recebe um objeto do {@link Folha}, abre uma session com o banco e deleta o
	 * objeto no mesmo.
	 * </p>
	 *
	 * @param folhaASerDeletada {@link Folha}, referente a {@link Folha} informada
	 * @see Folha
	 */
	public boolean delete(Folha folhaASerDeletada) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		try {
			session.delete(folhaASerDeletada);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * <h1>Atualiza um objeto {@link Folha} no banco.</h1>
	 *
	 * <p>
	 * Faz a ligacao com o banco de dados, recebe um objeto {@link Folha}, busca o
	 * mesmo no banco de dados atraves da id, e atualiza os novos valores que foram
	 * alterados.
	 * </p>
	 *
	 * @param folhaASerAlterada {@link Folha}, referente a {@link Folha}.
	 * @see Folha
	 */
	public boolean update(Folha folhaASerAlterada) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		try {
			session.update(folhaASerAlterada);
			session.getTransaction().commit();
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
	 * @return List<{@link Folha }> referente aos todos os dados do banco
	 * @see Folha
	 */
	public List<Folha> getAll() {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Folha> criteria = builder.createQuery(Folha.class);
		criteria.from(Folha.class);
		List<Folha> selectedFolhas = session.createQuery(criteria).getResultList();
		return selectedFolhas;
	}

	/**
	 * <h1>Pega uma {@link Folha} do banco atraves do Id.</h1>
	 *
	 * <p>
	 * Faz a conexao com o banco, recebe um parametro do tipo inteiro e retorna a
	 * {@link Folha} com o id correspondente.
	 * </p>
	 *
	 * @param id int, referente ao id informado
	 * @return {@link Folha}
	 * @see Folha
	 */
	public Folha getById(int id) {
		return session.get(Folha.class, id);
	}

	/**
	 * Traz todos as folhas por id
	 * @param idColaborador
	 * @return List<Folha>
	 * 
	 */
	public List<Folha> getAllById(int idColaborador) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Folha> criteria = builder.createQuery(Folha.class);
		criteria.from(Folha.class);
		List<Folha> folhaFiltrada = new ArrayList<Folha>();
		List<Folha> selectedFolhas = session.createQuery(criteria).getResultList();
		for (int i = 0; i < selectedFolhas.size(); i++) {
			if (selectedFolhas.get(i).getId() == idColaborador) {
				folhaFiltrada.add(selectedFolhas.get(i));
				return folhaFiltrada;
			}
		}	
		return folhaFiltrada;
	}

	/**
	 * <h1>Pega objetos do banco atraves da data de emissao.</h1>
	 *
	 * <p>
	 * Faz a conexao com o banco, recebe um paramentro do tipo LocalDate e retorna
	 * uma lista com as {@link Folha}s que possuem a data de emissao correspondente.
	 * </p>
	 *
	 * @param data LocalDate, referente a data informada.
	 * @return List<{ @ link Folha }>, referente a todas as folhas com a data
	 *         correspondente
	 * @see Folha
	 */
	public List<Folha> getByDate(LocalDate data) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Folha> criteria = builder.createQuery(Folha.class);

		Root<Folha> root = criteria.from(Folha.class);

		criteria.where(builder.equal(root.get("dataEmissao"), data));

		List<Folha> folha = session.createQuery(criteria).getResultList();

		return folha;
	}

	/***
	 * <h1>Pega {@link Folha}s por valores em uma coluna</h1>
	 *
	 * <p>
	 * Recebe uma column e um value. Procura no banco todas as {@link Folha}s que
	 * tenham esse valor nessa coluna. Retorna uma lista com todas as {@link Folha}s
	 * correspondentes.
	 * </p>
	 *
	 * @param column String, referente a coluna informada
	 * @param value  Double, referente ao valor inforamado
	 *
	 * @return List<{ @ link Folha }>, referente as folhas que tem o valor na coluna
	 *         informada.
	
	 * @see Folha
	 */
	public List<Folha> getDoubleByColumn(String column, Double value) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Folha> criteria = builder.createQuery(Folha.class);

		Root<Folha> root = criteria.from(Folha.class);

		// CriteriaQuery<Folha> rootQuery = criteria.select(root);

		Expression<?> columnExpression = (Expression<?>) root.get(column);

		criteria.select(root).where(builder.equal(columnExpression, value));

		List<Folha> folha = session.createQuery(criteria).getResultList();

		return folha;
	}

	/**
	 * <h1>Pega {@link Folha}s por um intervalo de valores em uma coluna</h1>
	 *
	 * <p>
	 * Recebe uma column, valueInicial e valueFinal. Procura no banco todas as
	 * {@link Folha}s que tenham esse intervalo de valor nessa coluna. Retorna uma
	 * lista com todas as {@link Folha}s correspondentes.
	 * </p>
	 *
	 * @param column       String, referente a coluna informada
	 * @param valueInicial Double, referente ao valor inicial informado
	 * @param valueFinal   Double, referente ao valor final inforamado
	 *
	 * @return List<{ @ link Folha }>, referente as folhas que tem o interalo de
	 *         valor na coluna informada.
	 *
	 * @author Sprint 6: David Willian <dwillian676@gmail.com;
	 * @author Sprint 6: Guilherme Ezequiel <guilhermeezequieldasilva@gmail.com>;
	 * @author Sprint 6: Jonata Caetano <jonatacaetano88@gmail.com>
	 * @author Sprint 6: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
	 * @author Sprint 6: Samuel Levi <samuel.levi@senior.com.br>
	 *
	 * @see Folha
	 */
	public List<Folha> getValuesBetween(String column, Double valueInicial, Double valueFinal) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Folha> criteria = builder.createQuery(Folha.class);

		Root<Folha> root = criteria.from(Folha.class);

		// CriteriaQuery<Folha> rootQuery = criteria.select(root);

		Expression<Double> columnExpression = root.get(column);

		criteria.select(root).where(builder.between(columnExpression, valueInicial, valueFinal));

		List<Folha> folha = session.createQuery(criteria).getResultList();

		return folha;
	}

	/**
	 * Metodo que limpa a tabela 
	 */
	public void limparTabela() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<Folha> criteriaDelete = builder.createCriteriaDelete(Folha.class);
		criteriaDelete.from(Folha.class);
		session.createQuery(criteriaDelete).executeUpdate();
	}
}