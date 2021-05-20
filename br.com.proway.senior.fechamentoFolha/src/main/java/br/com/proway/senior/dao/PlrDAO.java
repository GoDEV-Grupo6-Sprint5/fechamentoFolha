package br.com.proway.senior.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.proway.senior.model.Plr;

/***
 * 
 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
 *
 */
public final class PlrDAO {
	
	/**
	 * Instancia da classe FolhaDAO em Singleton
	 */
	private static PlrDAO instance;
	/**
	 * Sessao
	 */
	private Session session;

	/**
	 * Metodo getInstance que retorna uma instancia {@link FolhaDAO}. O método
	 * recebe uma {@link Session} Verifica se existe uma instancia. Se existir,
	 * retorna esta instancia. Se nao existir, cria e retorna uma nova instancia.
	 * 
	 * @param session of type {@link Session}
	 * @return instance {@link FolhaDAO}
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
	
	public void insert(Plr objectToInsert) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.save(objectToInsert);
		session.getTransaction().commit();
	}
	
	public void delete(int id) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(session.get(Plr.class, id));
		session.getTransaction().commit();
	}
	
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
	
	public Plr getById(int id) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		return session.get(Plr.class, id);
	}
	
	public List<Plr> getAll() {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Plr> criteria = builder.createQuery(Plr.class);
		criteria.from(Plr.class);
		List<Plr> selectedPlr = session.createQuery(criteria).getResultList();
		return selectedPlr;
	}
	
	public void update(Plr objectToUpdate) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(objectToUpdate);
		session.getTransaction().commit();
	}

	
	public void limparTabela() {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<Plr> criteriaDelete = builder.createCriteriaDelete(Plr.class);
		criteriaDelete.from(Plr.class);
		session.createQuery(criteriaDelete).executeUpdate();	
	}
}
