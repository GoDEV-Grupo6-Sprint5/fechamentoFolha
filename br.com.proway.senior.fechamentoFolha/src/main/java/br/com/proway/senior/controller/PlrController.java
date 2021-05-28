package br.com.proway.senior.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.proway.senior.dao.PlrDAO;
import br.com.proway.senior.dao.PostgresConnector;
import br.com.proway.senior.model.Plr;

/***
 * <h1>Controller responsavel pelos metodos da {@link Plr}</h1>
 * 
 * <p>Faz o CRUD para a classe {@link Plr}</p>
 * 
 * @author Sprint 5: Leonardo Felipe Silva <felipeleao217@gmail.com>;
 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
 * 
 * @see Plr
 *
 */
public class PlrController {
	
	/**
	 * <h1>Cadastra um novo {@link Plr} no banco de dados.</h1>
	 * 
	 * <p>Verifica se existe um {@link Plr} cadastrado para um detemirnado periodo. 
	 * Se sim, lanca uma exception, se nao, insere este {@link Plr} no banco de dados.</p>
	 * 
	 * @param data LocalDate, referente a data informada;
	 * @param valor double, referente ao valor informado.
	 * 
	 * @throws Exception - Caso o {@link Plr} j� exista no banco com
	 * a mesma data.
	 * 
	 * @author Sprint 5: Leonardo Felipe Silva <felipeleao217@gmail.com>;
	 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
	 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
	 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
	 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
	 * 
	 * @see Plr
	 */
	public void cadastrarPlr(LocalDate data, double valor) throws Exception {
		PlrDAO db = PlrDAO.getInstance(PostgresConnector.getSession());
		Plr plr = new Plr();
		plr.setVencimento(data);
		plr.setValorPlr(valor);
		
		ArrayList<Plr> plrsBuscados = (ArrayList<Plr>) db.getAll(); 
		if(!plrsBuscados.isEmpty()) {
			for (Plr plrProcurado : plrsBuscados) {
				if (plrProcurado.getVencimento().getYear() == data.getYear()) {
					if (plrProcurado.getVencimento().getMonth() == data.getMonth()) {
						throw new Exception("PLR ja cadastrado para este per�odo");
					}
				} 
			}
			db.insert(plr);
		} else {
			db.insert(plr);
		}
	}
	
	/**
	 * <h1>Busca todas as {@link Plr}s do banco de dados;</h1>
	 * 
	 * <p>Procura todas as {@link Plr}s no banco, as insere
	 * em uma lista e depois retorna a lista.</p>
	 * 
	 * @return ArrayList<Plr> referente a todas as {@link Plr}s do banco
	 * de dados
	 * 
	 * @throws Exception - Caso ocorra algum erro de obter o resultao.
	 * 
	 * @author Sprint 5: Leonardo Felipe Silva <felipeleao217@gmail.com>;
	 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
	 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
	 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
	 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
	 * 
	 * @see Plr
	 */
	public ArrayList<Plr> buscarTodosPlr() throws Exception {
		PlrDAO db = PlrDAO.getInstance(PostgresConnector.getSession());
		ArrayList<Plr> plrs = new ArrayList<Plr>();
		try {
			plrs = (ArrayList<Plr>) db.getAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return plrs;
	}
	
	/**
	 * <h1>Atualiza {@link Plr}.</h1>
	 * 
	 * <p>Recebe uma data e um valor, busca no banco de dados uma {@link Plr}
	 * com o mesmo periodo de data e altera o valor desta {@link Plr} com o valor
	 * do parametro.</p>
	 * 
	 * @param data LocalDate, referente a data informada.
	 * @param valor double, referente ao valor informado.
	 * 
	 * @throws Exception - Caso a {@link Plr} seja inexistente.
	 * 
	 * @author Sprint 5: Leonardo Felipe Silva <felipeleao217@gmail.com>;
	 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
	 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
	 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
	 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
	 * 
	 * @see Plr
	 */
	public void atualizarPlr(LocalDate data, double valor) throws Exception {
		PlrDAO db = PlrDAO.getInstance(PostgresConnector.getSession());
		boolean plrExiste = false;
		ArrayList<Plr> plrs = (ArrayList<Plr>) db.getAll();
		
		for(Plr plr : plrs) {
			if(plr.getVencimento().getYear() == data.getYear()) {
				if(plr.getVencimento().getMonth() == data.getMonth()) {
					plrExiste = true;
				}
			}
		}
		if(plrExiste) {
			for(Plr plr : plrs) {
				if(plr.getVencimento().getYear() == data.getYear()) {
					if(plr.getVencimento().getMonth() == data.getMonth()) {
						plr.setValorPlr(valor);
						db.update(plr);
					}
				}
			}
		} else {
			throw new Exception("Impossivel atualizar: Plr inexistente.");
		}
	}
	
	/**
	 * <h1>Busca o valor de uma {@link Plr} atraves
	 * da data passada no parametro.</h1>
	 * 
	 * <p>Recebe uma data e busca o valor da {@link Plr}
	 * que possui a mesma data no banco de dados. Retorna
	 * o valor da {@link Plr} encontrada.</p>
	 * 
	 * @return double, referente ao valor da {@link Plr}
	 * 
	 * @author Sprint 5: Leonardo Felipe Silva <felipeleao217@gmail.com>;
	 * @author Sprint 5: Bruna Carvalho <sh4323202@gmail.com>;
	 * @author Sprint 5: Leonardo Pereira <leonardopereirajr@gmail.com>;
	 * @author Sprint 5: Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
	 * @author Sprint 5: Lucas Nunes <lucasnunes.ln365@gmail.com>.
	 * 
	 * @see Plr
	 */
	public double getValorPlrMes(LocalDate date) {
		PlrDAO db = PlrDAO.getInstance(PostgresConnector.getSession());
		ArrayList<Plr> plrs = (ArrayList<Plr>) db.getAll();
		
		for(Plr plr : plrs) {
			if(plr.getVencimento().getYear() == date.getYear()) {
				if(plr.getVencimento().getMonth() == date.getMonth()) {
					return plr.getValorPlr();
				}
			}
 		}
		
		return 0.0;
	}
}