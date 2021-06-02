package br.com.proway.senior.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.proway.senior.model.Bonificacao;
import br.com.proway.senior.model.Folha;
import br.com.proway.senior.model.Plr;

/**
 * PostgresConnector.
 * 
 * Classe que contém a implementação do JDBC para acessar o banco postgres.
 * 
 * @author Bruno Oliveira
 * @author Leonado Pereira
 * 
 * Sprint 5:
 * @author Leonardo Felipe Silva <felipeleao217@gmail.com>;
 * @author Bruna Carvalho <sh4323202@gmail.com>;
 * @author Leonardo Pereira <leonardopereirajr@gmail.com>;
 * @author Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
 * @author Lucas Nunes <lucasnunes.ln365@gmail.com>.
 */
public class PostgresConnector {

	private static SessionFactory sessionFactory;

	private static Session session;

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
					.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/fechamento_folha")
					.setProperty("hibernate.connection.username", "postgres")
					.setProperty("hibernate.connection.password", "admin")
					.setProperty("hibernate.jdbc.time_zone", "UTC")
					.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
					.setProperty("hibernate.show_sql", "true")
					.setProperty("hibernate.format_sql", "false")
					.setProperty("hibernate.hbm2ddl.auto", "update")
					.setProperty("hibernate.connection.autocommit", "true")
					.addAnnotatedClass(Folha.class)
					.addAnnotatedClass(Plr.class)
					.addAnnotatedClass(Bonificacao.class)
					.buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Initial SessionFactory creation failed: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = buildSessionFactory();
		return sessionFactory;
	}

	public static Session getSession() {
		getSessionFactory();
		if (session == null)
			session = sessionFactory.openSession();
		return session;
	}
}