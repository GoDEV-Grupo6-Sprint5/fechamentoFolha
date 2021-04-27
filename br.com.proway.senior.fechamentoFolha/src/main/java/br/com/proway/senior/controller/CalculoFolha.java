package br.com.proway.senior.controller;

public class CalculoFolha { 
	
//	calcular(){
//		PontoFolha ponto = new PontoFolha();
//		ColaboradorFolha colab = new ColaboradorFolha();
//		Folha folha = new Folha(colab, ponto);
//	}
	
	
	/**
	 * Calcula a folha final
	 * 
	 * M�todo respons�vel por chamar todos os outros m�todos que calculam o
	 * fechamento da folha do mes.
	 * 
	 * @return Sal�rio liquido do coaborador
	 */
	public double calcularFolha() {

		this.salarioBruto += this.calcularValorDasHorasTrabalhadas();
		this.salarioBruto -= this.calcularValorHorasFaltas();
		this.salarioBruto += this.calcularValorHorasExtras();
		this.salarioBruto += this.calcularDSR();
		this.salarioBruto += this.calcularBonificacao();
		this.salarioBruto -= this.calcularDescontoInss();
		this.salarioBruto -= this.calcularDescontoImpostoRenda();
		this.salarioBruto -= this.calcularDescontoPlanoSaude();
		this.salarioBruto -= this.calcularDescontoValeTransporte();
		this.salarioLiquido = this.salarioBruto;
		this.setDataEmissao();

		return this.salarioLiquido;
	}

}
