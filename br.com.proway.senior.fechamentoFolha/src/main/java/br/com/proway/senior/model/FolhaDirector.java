package br.com.proway.senior.model;

import br.com.proway.senior.model.externo.ICargoFolha;
import br.com.proway.senior.model.externo.IColaboradorFolha;
import br.com.proway.senior.model.externo.IFeriasFolha;
import br.com.proway.senior.model.externo.IPontoFolha;

/**
 * FolhaDirector
 * 
 * � uma classe que cont�m a l�gica de dire��o da constru��o de uma Folha
 * seguindo o design pattern builder com director.
 * 
 * @author Lucas Grij�
 * @author Lucas Walim
 * @author Marcelo Schaefer
 */
public class FolhaDirector {

	private IFolhaBuilder builder;

	/**
	 * M�todo Construtor
	 * 
	 * @author Lucas Grij�
	 * @author Lucas Walim
	 * @author Marcelo Schaefer
	 */
	public FolhaDirector(IFolhaBuilder builder) {
		this.builder = builder;
	};

	/**
	 * Criar folha normal
	 * 
	 * Constr�i uma folha regular ou seja, sem qualquer contabilidade de f�rias.
	 * 
	 * @author Lucas Grij�
	 * @author Lucas Walim
	 * @author Marcelo Schaefer
	 */	
	public void createFolhaNormal(IColaboradorFolha colaborador, IPontoFolha ponto, ICargoFolha cargo) {
		builder.iniciarCalculos(colaborador, cargo);
		builder.calcularHorasNormais(ponto, cargo);
		builder.calcularDescontoNormal(colaborador, cargo);
		builder.build();
	}

	/**
	 * Criar folha f�rias
	 * 
	 * Constr�i uma folha exclusiva de f�rias.
	 * 
	 * @author Lucas Grij�
	 * @author Lucas Walim
	 * @author Marcelo Schaefer
	 */	
	public void createFolhaFerias(IColaboradorFolha colaborador, ICargoFolha cargo, IFeriasFolha ferias) {
		builder.iniciarCalculos(colaborador, cargo);
		builder.calcularHorasFerias(ferias);
		builder.calcularDescontoFerias(colaborador);
		builder.build();
	}

	/**
	 * Criar folha hibrida
	 * 
	 * Constr�i uma folha que contabiliza tanto horas de trabalho normais quanto horas de f�rias.
	 * 
	 * @author Lucas Grij�
	 * @author Lucas Walim
	 * @author Marcelo Schaefer
	 */	
	public void createFolhaHibrida(IColaboradorFolha colaborador, IPontoFolha ponto, ICargoFolha cargo,
			IFeriasFolha ferias) {
		builder.iniciarCalculos(colaborador, cargo);
		builder.calcularHorasNormais(ponto, cargo);
		builder.calcularDescontoNormal(colaborador, cargo);
		builder.calcularHorasFerias(ferias);
		builder.calcularDescontoFerias(colaborador);
		builder.build();
	}

}
