package br.com.proway.senior.fechamentoFolha;

public final class Filtro implements InterfaceFiltro {	
	
	/**
	 * Filtro de Usu�rios por e-mail
	 * 
	 * Recebe os par�mtros para verficar se o colaborador existe e o retorna
	 * 
	 * @param email; referente ao colaborador que o usu�rio deseja saber sobre 
	 * @param cadastro; lista de todos os colaboradores cadastrados
	 * 
	 * @return Colaborador/null colaborador desejado/colaborador n�o existe
	 */	
	public Colaborador filtro(String email, CadastroColaborador cadastro) { 
		for(Colaborador c : cadastro.getColaboradores()) {
			if(c.getEmail() == email) {				
				return c; 
			}
		}
		return null;  
	}

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
	 * @return Folha/null Folha desejado/Folha n�o existe
	 */		
	public Folha filtro(Colaborador c, int idFolha) {
		for(Folha folha : c.getTotalFolhas()) {
			if(folha.getId() == idFolha) {
				return folha;
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
	 * @param data; Data da folha desejada
	 * 
	 * @return Folha/null Folha desejado/Folha n�o existe
	 */	
	public Folha filtro(Colaborador c, String data) {
		for(Folha folha : c.getTotalFolhas()) {
			if(data == folha.getData()) {			
				return folha;
			}
		}
		return null;
	}
	
}