package br.com.proway.senior.retirados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.proway.senior.model.Folha;

/**
 * PostgresConnector
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

	static String url = "jdbc:postgresql://localhost:5432/FechamentoFolha";
	static String urlTest = "jdbc:postgresql://localhost:5432/testFechamentoFolha";
	static String user = "postgres";
	static String password = "admin";
	public static Connection con;

	/**
	 * Connect
	 * 
	 * Realiza coneção com o banco.
	 * 
	 * @author Bruno Oliveira
	 * @author Leonado Pereira
	 * @throws SQLException
	 */
	public static void connect() throws SQLException {
		con = DriverManager.getConnection(url, user, password);
	}
	
	public static void connectTest() throws SQLException {
		con = DriverManager.getConnection(urlTest, user, password);
	}

	/**
	 * Executa Query
	 * 
	 * Realiza uma busca (query) no banco e returna um resultset.
	 * 
	 * @param String query
	 * @return ResultSet
	 * @throws SQLException
	 * 
	 * @author Bruno Oliveira
	 * @author Leonado Pereira
	 */
	public static ResultSet executeQuery(String query) throws SQLException {
		if (con == null) {
			connect();
		}
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	/**
	 * Atualiza por Query
	 * 
	 * Realiza uma query no banco, não tem retorno.
	 * 
	 * @param String query
	 * @throws SQLException
	 * 
	 * @author Bruno Oliveira
	 * @author Leonado Pereira
	 */
	public static void executeUpdate(String query) throws SQLException {
		if (con == null) {
			connect();
		}
		Statement st = con.createStatement();
		st.executeUpdate(query);
	}
	
	public static void executeUpdateTest(String query) throws SQLException {
		if (con == null) {
			connectTest();
		}
		Statement st = con.createStatement();
		st.executeUpdate(query);
	}

	/**
	 * Verificar versão
	 * 
	 * Verifica a versão do banco de dados.
	 * 
	 * @return String version
	 * @author Bruno Oliveira
	 * @author Leonado Pereira
	 */
	public static String dbVersion() {
		try {
			if (con == null) {
				connect();
			}
			String query = "SELECT VERSION()";
			ResultSet rs = executeQuery(query);
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}