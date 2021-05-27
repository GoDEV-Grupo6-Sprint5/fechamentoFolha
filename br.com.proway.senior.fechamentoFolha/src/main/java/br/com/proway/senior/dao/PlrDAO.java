package br.com.proway.senior.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.proway.senior.model.Folha;
import br.com.proway.senior.model.IPlr;
import br.com.proway.senior.model.Plr;
import org.springframework.stereotype.Repository;

/**
 * Possue os metodos CRUD da {@link Plr}.
 * </p>
 *
 * @author Sprint 6: David Willian <dwillian676@gmail.com>;
 * @author Sprint 6: Guilherme Ezequiel <guilhermeezequieldasilva@gmail.com>;
 * @author Sprint 6: Jonata Caetano <jonatacaetano88@gmail.com>
 * @author Sprint 6: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
 * @author Sprint 6: Samuel Levi <samuel.levi@senior.com.br>
 */
@Repository
public final class PlrDAO implements InterfaceDAO<Plr> {

	/**
	 * Instancia da classe FolhaDAO em Singleton
	 */
	private static PlrDAO instance;
	/**
	 * Sessao
	 */
	private Session session;

	/**
	 * Metodo getInstance que retorna uma instancia {@link PlrDAO}. O metodo recebe
	 * uma {@link Session} Verifica se existe uma instancia. Se existir, retorna
	 * esta instancia. Se nao existir, cria e retorna uma nova instancia.
	 * 
	 * @param session of type {@link Session}
	 * @return instance {@link PlrDAO}
	 */
	public static PlrDAO getInstance(Session session) {
		if (instance == null) {
			instance = new PlrDAO(session);
		}
		return instance;
	}

	private PlrDAO(Session session) {
		this.session = session;

	}

	/**
	 * <h1>Insere um {@link IPlr} no banco de dados.</h1>
	 *
	 * <p>
	 * Recebe um objeto {@link Plr}, abre uma session e insere o objeto no banco de
	 * dados.
	 * </p>
	 * 
	 * @param plrASerInserido {@link Plr}.
	 * 
	 * @see Plr
	 */
	public boolean insert(Plr plrASerInserido) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		try {
			session.save(plrASerInserido);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * <h1>Deleta um objeto {@link Plr} do banco de dados.</h1>
	 *
	 * <p>
	 * Recebe um objeto do tipo {@link Plr}, abre uma session com o banco e deleta o
	 * objeto no mesmo.
	 * </p>
	 *
	 * @param plrASerDeletado {@link Plr}.
	 * @see Plr
	 */
	public boolean delete(Plr plrASerDeletado) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		try {
			session.delete(plrASerDeletado);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * <h1>Atualiza um objeto {@link Plr} no banco.</h1>
	 *
	 * <p>
	 * Faz a ligacao com o banco de dados, recebe um objeto {@link Plr}, busca o
	 * mesmo no banco de dados atraves da id, e atualiza os novos valores que foram
	 * alterados.
	 * </p>
	 *
	 * @param plrASerAlterado {@link Plr}.
	 * @see Plr
	 */
	public boolean update(Plr plrASerAlterado) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		try {
			session.update(plrASerAlterado);
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
	 * @return List<{@link Plr }> referente aos todos os dados do banco
	 * @see Plr
	 */
	public List<Plr> getAll() {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Plr> criteria = builder.createQuery(Plr.class);
		criteria.from(Plr.class);
		List<Plr> selectedPlr = session.createQuery(criteria).getResultList();
		return selectedPlr;
	}

	/**
	 * <h1>Pega objetos do banco atraves da data de emissao.</h1>
	 *
	 * <p>
	 * Faz a conexao com o banco, recebe um paramentro do tipo LocalDate e retorna
	 * uma lista com as {@link Plr}s que possuem a data de emissao correspondente.
	 * </p>
	 *
	 * @param data LocalDate, referente a data informada.
	 * @return List<{ @ link Plr }>, referente a todas as folhas com a data
	 *         correspondente
	 * 
	 * @see Plr
	 */
	public List<Plr> getByDate(LocalDate data) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Plr> criteria = builder.createQuery(Plr.class);
		Root<Plr> root = criteria.from(Plr.class);

		criteria.where(builder.equal(root.get("vencimento"), data));

		List<Plr> plr = session.createQuery(criteria).getResultList();
		return plr;
	}

	/**
	 * <h1>Pega uma {@link Plr} do banco atraves do Id.</h1>
	 *
	 * <p>
	 * Faz a conexao com o banco, recebe um parametro do tipo inteiro e retorna a
	 * {@link Plr} com o id correspondente.
	 * </p>
	 *
	 * @param id int, referente ao id informado
	 * @return {@link Plr}
	 * @see Folha
	 */
	public Plr getById(int id) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		return session.get(Plr.class, id);
	}

	public void limparTabela() {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<Plr> criteriaDelete = builder.createCriteriaDelete(Plr.class);
		criteriaDelete.from(Plr.class);
		session.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * <h1>Retorna todos os objetos do banco de dados atraves do Id</h1>.
	 *
	 * <p>
	 * Faz a conexao com o banco, tras todos os bancos e os lista em um List.
	 * </p>
	 *
	 * @return List<{@link Plr }> referente aos todos os dados do banco
	 * @see Folha
	 */
	public List<Plr> getAllById(int id) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Plr> criteria = builder.createQuery(Plr.class);
		criteria.from(Plr.class);
		List<Plr> plrFiltrado = new ArrayList<Plr>();
		List<Plr> selectedPlr = session.createQuery(criteria).getResultList();
		for (int i = 0; i < selectedPlr.size(); i++) {
			if (selectedPlr.get(i).getId() == id) {
				plrFiltrado.add(selectedPlr.get(i));
				return plrFiltrado;
			}
		}
		return plrFiltrado;
	}
}
