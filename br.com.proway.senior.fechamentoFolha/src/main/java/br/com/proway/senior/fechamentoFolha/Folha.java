package br.com.proway.senior.fechamentoFolha;

import java.time.LocalDateTime;

public class Folha {
	int id;
	LocalDateTime data;
	double salarioLiquido;
	double salarioBruto;
	double valorHoras;
	double horasTrabalhadas;
	double horasExtra;
	double horasFalta;
	double valorBonificacao;
	double planoSaude;
	double percentualInsalubridade;
	double valorInsalubridade;
	double inss;
	double impostoDeRenda;
	double valorMensalidade;
	double valorCoparticipacao;
	double valeTransporte;
	double salarioMinimo;

	
	/**
	 * Calcula o valor de vale transporte a ser descontado do colaborador
	 * 
	 * Chamada do metodo de Vale transporte que calcula o desconto, se o percentual
	 * aplicado de 0.06% for maior que R$ 180,00 o desconto ser� este, se for menor
	 * retorna este valor calculado. Falta ser inclu�do no c�lculo final da folha.
	 * 
	 * @param valetransporte
	 * @return valetransporte
	 */
	public double calculaValeTransporte() {
		if ((0.06 * this.salarioBruto) >= 180) {
		return this.valeTransporte = 180;
		} else {
			this.valeTransporte = this.salarioBruto * 0.06 ;
		}
		return this.valeTransporte;
	}
	
	/**
	 * Desconto de Plano de Saude.
	 * 
	 * Realiza o desconto de plano de saude, repassando o 1� par�metro como valor de
	 * mensalidade, e o 2� par�metro o valor de cooparticipacao caso exista. A
	 * variavel totalDescontoPlanoSaude retornara a soma dos dois par�metros.
	 * 
	 * @param valorMensalidade    double do colaborador
	 * @param valorCoparticipacao double adicional do colaborador
	 * @return retorna valor a ser descontado em folha, referente ao Plano de Saude
	 */
	public double descontaPlanoSaude() {
		double totalDescontoPlanoSaude = this.valorMensalidade + this.valorCoparticipacao;
		return totalDescontoPlanoSaude;
	}
	
	/**
	 * Recebe o valor de bonifica��o a ser acrescido na folha do colaborador
	 * 
	 * Recebe o valor da bonifica��o que ser� aplicado posteriormente nos proventos
	 * do colaborador.
	 * 
	 * @param valorBonificacao double Valor que o colaborador receber� de
	 *                         bonifica��o
	 * @return Retorna o valor de bonifica��o que ser� somado aos demais proventos
	 *         na folha do colaborador
	 */
	public double adicionaBonificacao() {
		
		if(valorBonificacao > 0) {
			return this.valorBonificacao;	
		}else {
			return this.valorBonificacao = 0;	
		}
	}
	
	/**
	 * Calcula o valor de INSS a ser descontado
	 * 
	 * Realiza o c�lculo do valor de INSS a ser descontado em folha a partir do
	 * sal�rio informado. Valor de desconto fixado em 11%. Retorna o valor a ser
	 * descontado.
	 * 
	 * @param valorSalarioBruto double Sal�rio bruto do colaborador, inluindo os
	 *                          demais proventos como horas extras e bonifica��o.
	 * @return Retorna o valor a ser descontado em folha.
	 */
	public double descontoInss() {
		double calculaInss = this.salarioBruto * 0.11;
		return this.inss;
	}
	
	/**
	 * Calcula o valor de Imposto de Renda a ser descontado em folha
	 * 
	 * Realiza o c�lculo do valor a ser descontado referente ao Imposto de Renda na
	 * folha do colaborador, a partir do sal�rio bruto do colaborador.
	 * 
	 * @param valorSalarioBruto double Valor do sal�rio bruto do colaborador,
	 *                          inluindo os demais proventos como horas extras e
	 *                          bonifica��o.
	 * @return Retorna o valor que a ser descontado em folha referente ao Imposto de
	 *         Renda.
	 */
	public double calculaImpostoRenda() { //**********************
		double resultado;

		if (this.salarioBruto <= 1903.98) {
			resultado = 0;
		} else if (this.salarioBruto >= 1903.98 && this.salarioBruto <= 2826.65) {
			resultado = (this.salarioBruto * 0.075) - 142.80;
		} else if (this.salarioBruto >= 2826.66 && this.salarioBruto <= 3751.05) {
			resultado = (this.salarioBruto * 0.15) - 354.80;
		} else if (this.salarioBruto >= 3751.06 && this.salarioBruto <= 4664.68) {
			resultado = (this.salarioBruto * 0.225) - 636.13;
		} else {
			resultado = (this.salarioBruto * 0.275) - 869.36;
		}

		return resultado;
	}
	
	/**
	 * Metodo para Calcular insalubridade Este metodo recebera diferentes
	 * percentuais( 10%, 20% ou 40%) de insalubridade para ao final retornar o valor
	 * com base na multiplica��o pelo Salario Minimo
	 * 
	 * @param percentual
	 * @return
	 */
	public  double valorInsalubridade(double percentualInsalubridade) {
		if (this.percentualInsalubridade == 10) {
			return this.valorInsalubridade = this.salarioMinimo * 0.10;
		} else if (this.percentualInsalubridade == 20) {
			return this.valorInsalubridade = this.salarioMinimo * 0.20;
		} else if (this.percentualInsalubridade == 40) {
			return this.valorInsalubridade = this.salarioMinimo * 0.40;
		} else {
			return this.valorInsalubridade = 0;
		}
	}
	
	/**
	 * Calcula hora normal somando insalubridade.
	 * 
	 * Realiza calculo para somar valor hora de insalubridade junto a hora normal.
	 * Recebe tr�s par�metros: 1� valorHoraColab, 2� quantidadeHorasTrabalhadas, 3�
	 * percentualInsalubridade. Atraves do percentualInsalubridade receberemos o
	 * valorInsalubridade calculado sobre o salario m�nimo. Caso o valor seja 10, 20
	 * ou 40, a variavel valorHoraComInsalubridade recebe valorHora +
	 * (valorInsalubridade / quantidadeHorasTrabalhadas), retornando o
	 * valorHoraComInsalubridade. Caso seja um valor diferente, valorInsalubridade
	 * recebera 0, e valorHora multiplicara quantidadeHorasTrabalhadas retornando o
	 * valorHora
	 *
	 * @param valorHora                  double
	 * @param quantidadeHorasTrabalhadas double
	 * @param percentualInsalubridade    double
	 * @return
	 */
	public double calculaHoraComInsalubridade() {
		double valorHoraComInsalubridade = 0;
		double valorInsalubridade = valorInsalubridade(this.percentualInsalubridade);
		if (this.valorInsalubridade > 0) {
			valorHoraComInsalubridade = this.valorHoras + (this.valorInsalubridade / this.horasTrabalhadas);
		} else if (this.valorInsalubridade == 0) {
			this.salarioBruto = this.valorHoras * this.horasTrabalhadas;
		}
		return valorHoraComInsalubridade;
	}
	
	/**
	 * Calcula o valor inicial do sal�rio
	 * 
	 * Realiza o c�lculo do valor inicial do sal�rio considerando a quantidade de
	 * horas trabalhadas vezes valor da hora com insalubridade do colaborador. O
	 * valor retornado � base para construir o sal�rio bruto do colaborador e
	 * realizar os demais c�lculos para o fechamento.
	 * 
	 * @param quantidadeHorasTrabalhadas double Quantidade horas trabalhadas no m�s
	 *                                   conforme escala do colaborador.
	 * @param valorHoraComInsalubridade  double Valor da hora do colaborador j�
	 *                                   acrescido da insalubridade.
	 * @return Retorna o valor do sal�rio inicial, considerando apenas a quantidade
	 *         horas trabalhadas e o valor da hora com insalubridade. Demais
	 *         proventos ser� acrescentados no c�lculo final da folha.
	 */
	public double calculaHorasTrabalhadas() {
		double salarioBruto = this.horasTrabalhadas * this.valorInsalubridade;
		return salarioBruto;
	}
	
	/**
	 * Calcula o valor a ser descontado de horas faltas
	 * 
	 * Realiza o c�lculo das horas faltas a serem descontadas na folha do
	 * colaborador, recebe o valor da hora acrescido da insalubridade (se houver) e
	 * multiplica pela quantidade de horas faltas informadas.
	 * 
	 * @param horaComInsalubridade double Valor da hora do colaborador acrescido da
	 *                             insalubridade.
	 * @param quantidadeHrsFaltas  double Quantidade de horas faltas.
	 * @return Retorna o valor a ser descontado na folha do colaborador referente as
	 *         horas faltas.
	 */
	public double valorHorasFaltas() {
		double valorHorasFaltas = this.horasFalta * this.valorInsalubridade;
		double valorDiferencaSalario = this.horasFalta;
		return valorDiferencaSalario;
	}


	

}
