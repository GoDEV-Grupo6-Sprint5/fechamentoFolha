package br.com.proway.senior.dto;

import br.com.proway.senior.model.Folha;

/**
 * <p>Classe que instancia os atributos da {@link Folha}, do ponto, do colaborador e da bonificação.</p>
 * 
 * @author Sprint 6: <a href="mailto:dwillian676@gmail.com?subject=FolhaDTO" title="">David Willian</a>
 * @author Sprint 6: <a href="mailto:guilhermeezequieldasilva@gmail.com?subject=FolhaDTO" title="">Guilherme Ezequiel</a>
 * @author Sprint 6: <a href="mailto:jonatacaetano88@gmail.com?subject=FolhaDTO" title="">Jônata Caetano</a>
 * @author Sprint 6: <a href="mailto:sabrinaschmidt335@gmail.com?subject=FolhaDTO" title="">Sabrina Schmidt</a>
 * @author Sprint 6: <a href="mailto:samuel.levi@senior.com.br?subject=FolhaDTO" title="">Samuel Levi</a>
 * 
 */
public class FolhaDto {

	/**
	 * Atributos referente a {@link Folha}.
	 */
	private double valorHorasTrabalhadas;
	private double valorHorasExtras;
	private double valorInss;
	private double valorImpostoDeRenda;
	private double valorPlanoSaude;
	private double valorValeTransporte;
	private double salarioBruto;
	private double salarioLiquido;
	private double valorFGTS;
	private double bonificacaoColaborador;
	private double plr;
	
	/**
	 * Construtor {@link FolhaDto} a partir da {@link Folha}, do colaborador, da bonificação,
	 * do PLR e do ponto
	 */
	public FolhaDto(Folha folha) {
		//Folha
		this.valorHorasTrabalhadas = folha.getValorHorasTrabalhadas();
		this.valorHorasExtras = folha.getValorHorasExtras();
		this.valorInss = folha.getValorInss();
		this.valorImpostoDeRenda = folha.getValorImpostoDeRenda();
		this.valorPlanoSaude = folha.getValorPlanoSaude();
		this.valorValeTransporte = folha.getValorValeTransporte();
		this.salarioBruto = folha.getSalarioBruto();
		this.salarioLiquido = folha.getSalarioLiquido();
		this.valorFGTS = folha.getValorFGTS();
		
	}

	public FolhaDto() {
	}


	public double getValorHorasTrabalhadas() {
		return valorHorasTrabalhadas;
	}

	public double getValorHorasExtras() {
		return valorHorasExtras;
	}

	public double getValorInss() {
		return valorInss;
	}

	public double getValorImpostoDeRenda() {
		return valorImpostoDeRenda;
	}

	public double getValorPlanoSaude() {
		return valorPlanoSaude;
	}

	public double getValorValeTransporte() {
		return valorValeTransporte;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public double getValorFGTS() {
		return valorFGTS;
	}

	public double getBonificacaoColaborador() {
		return bonificacaoColaborador;
	}

	public double getPlr() {
		return plr;
	}
	
}
