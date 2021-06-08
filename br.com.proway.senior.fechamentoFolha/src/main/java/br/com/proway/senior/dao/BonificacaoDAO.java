package br.com.proway.senior.dao;

import br.com.proway.senior.model.Bonificacao;
import br.com.proway.senior.model.Folha;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Bonificacao DAO.</h1>
 *
 * <p>
 * Classe responsavel por implementar os metodos CRUD da classe
 * {@link Bonificacao}.
 * </p>
 *
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @version Sprint 6
 * @see Bonificacao
 */
public class BonificacaoDAO implements InterfaceDAO<Bonificacao> {

	private static BonificacaoDAO instance;
	private final Session session;

	private BonificacaoDAO(Session session) {
		this.session = session;
	}

	public static BonificacaoDAO getInstance(Session session) {
		if (instance == null) {
			instance = new BonificacaoDAO(session);
		}
		return instance;
	}

	/**
	 * Inserir {@link Bonificacao} na tabela bonificação.
	 * <p>
	 * Recebe um objeto {@link Bonificacao} e insere ele na tabela de bonificações
	 * do banco de dados.
	 *
	 * @param bonificacaoASerInserida {@link Bonificacao}, referente a
	 *                                {@link Bonificacao} informada.
	 * @return
	 * @see Bonificacao
	 */
	public boolean insert(Bonificacao bonificacaoASerInserida) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
			try {
				session.save(bonificacaoASerInserida);
				session.getTransaction().commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean update(Bonificacao bonificacaoASerAlterada) {
		session.clear();
		if (!session.getTransaction().isActive()) 
			session.beginTransaction();
		try {
			session.update(bonificacaoASerAlterada);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Metodo que deleta
	 */
	public boolean delete(Bonificacao bonificacaoASerDeletada) {
		session.clear();
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
			try {
				session.delete(bonificacaoASerDeletada);
				session.getTransaction().commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}
		return false;
	}

	/**
	 * Metodoq que traz todos os objetos de bonificacao
	 * 
	 * @return List<Bonificacao>
	 */
	public List<Bonificacao> getAll() {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Bonificacao> criteria = builder.createQuery(Bonificacao.class);
		criteria.from(Bonificacao.class);
		return session.createQuery(criteria).getResultList();
	}

	/**
	 * Metodo que traz uma bonificacao pelo id
	 * 
	 * @param id
	 * @return Bonificacao
	 */
	public Bonificacao getById(int id) {
		return session.get(Bonificacao.class, id);
	}

	/**
	 * Retorna todas as bonificações recebidas por uma bonificacao.
	 *
	 * @param id
	 * @return List<Bonificacao>
	 */

	public List<Bonificacao> getAllById(int id) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Bonificacao> criteria = builder.createQuery(Bonificacao.class);
		criteria.from(Bonificacao.class);
		List<Bonificacao> bonificacaoFiltrada = new ArrayList<Bonificacao>();
		List<Bonificacao> selectedBonificacoes = session.createQuery(criteria).getResultList();
		for (Bonificacao selectedBonificacao : selectedBonificacoes) {
			if (selectedBonificacao.getId() == id) {
				bonificacaoFiltrada.add(selectedBonificacao);
				return bonificacaoFiltrada;
			}
		}
		return bonificacaoFiltrada;
	}

	/**
	 * Metodo que limpa a tabela a cada Objeto folha instanciado
	 */
	public void limparTabela() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<Bonificacao> criteriaDelete = builder.createCriteriaDelete(Bonificacao.class);
		criteriaDelete.from(Bonificacao.class);
		session.createQuery(criteriaDelete).executeUpdate();
	}

}
