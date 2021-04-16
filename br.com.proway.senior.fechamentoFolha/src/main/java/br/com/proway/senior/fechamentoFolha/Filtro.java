package br.com.proway.senior.fechamentoFolha;

public class Filtro {	
	
	/**
	 * Filtro de Usu�rios
	 * 
	 * Recebe os par�mtros para verficar se o colaborador existe e o retorna 
	 * 
	 * @param userId; ID referente ao colaborador que o usu�rio deseja saber sobre 
	 * @param cadastro; lista de todos os colaboradores cadastrados
	 * 
	 * @return Colaborador/null Colaborador desejado/Colaborador n�o existe
	 */	
	public Colaborador filtro(int userId, CadastroColaborador cadastro) {
		for(Colaborador c : cadastro.getColaboradores()) {
			if(c.getId() == userId) {			
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Filtro de Folhas
	 * 
	 * Recebe os par�mtros para verficar se a folha de determinado colaborador existe e a retorma 
	 * 
	 * @param c; Colaborador dono da folha
	 * @param idFolha; ID da folha desejada
	 * 
	 * @return Folha/null folha desejado/folha n�o existe
	 */
	
	//@Override0
	public Folha filtro(Colaborador c, int idFolha) {
		for(Folha folha : c.getTotalFolhas()) {
			if(folha.getId() == idFolha) {
				return folha;
			}
		}
		return null;  
	}

}


// In�cio: C�digo comentados

/**
 * Filtro de folha
 * 
 * Recebe os par�mtros para verficar se o colaborador e determinada folha do mesmo existe 
 * 
 * @param userId ID referente ao colaborador que o usu�rio deseja saber sobre 
 * @param folhaId ID referente a folha do mesmo usu�rio  
 * @param cadastro lista de todos os colaboradores cadastrados
 * @return folha/null folha desejada/folha n�o existe
 */
/*public Folha getFolhaPorColaborador(Integer userId, Integer folhaId, CadastroColaborador cadastro) {
	for(Colaborador c : cadastro.getColaboradores()) {
		if(c.getId() == userId) {
			for(Folha folha : c.getTotalFolhas()) {
				if(folha.getId() == folhaId) {
					return folha;
				}
			}
		}
	}
	return null;  
}
*/

/**
 * Filtro de Usu�rios Nome
 * 
 * Recebe os par�mtros para verficar se o colaborador existe e o retorma 
 * 
 * @param userId ID referente ao colaborador que o usu�rio deseja saber sobre 
 * @param cadastro lista de todos os colaboradores cadastrados
 * 
 * @return Colaborador/null colaborador desejado/colaborador n�o existe
 */

/*
public Colaborador getDadosPorNome(String nome, CadastroColaborador cadastro) { // String
	for(Colaborador c : cadastro.getColaboradores()) {
		if(c.getNome() == nome) {				
			return c; // toString 
		}
	}
	return null;  
}
*/

// Fim: C�digos comentados