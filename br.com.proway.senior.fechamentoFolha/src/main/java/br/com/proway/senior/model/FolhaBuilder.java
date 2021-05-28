package br.com.proway.senior.model;

import java.time.LocalDate;

import br.com.proway.senior.controller.PlrController;
import br.com.proway.senior.controller.calculos.CalculoDesconto;
import br.com.proway.senior.controller.calculos.CalculoHoras;
import br.com.proway.senior.controller.calculos.ICalculoDesconto;
import br.com.proway.senior.controller.calculos.ICalculoHoras;
import br.com.proway.senior.model.externo.interfaces.ICargoFolha;
import br.com.proway.senior.model.externo.interfaces.IColaboradorFolha;
import br.com.proway.senior.model.externo.interfaces.IFeriasFolha;
import br.com.proway.senior.model.externo.interfaces.IPontoFolha;

/**
 * FolhaBuilder
 * 
 * E uma classe que contem a logica de construcao de uma Folha seguindo 
 * o design pattern builder.
 * 
 * @author Lucas Grij�
 * @author Lucas Walim
 * @author Marcelo Schaefer
 * @author Leonardo Pereira
 *
 *         Sprint 5:
 * @author Leonardo Felipe Silva <felipeleao217@gmail.com>;
 * @author Bruna Carvalho <sh4323202@gmail.com>;
 * @author Leonardo Pereira <leonardopereirajr@gmail.com>;
 * @author Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
 * @author Lucas Nunes <lucasnunes.ln365@gmail.com>.
 *
 */
public class FolhaBuilder implements IFolhaBuilder {

	private int id; //
	private Integer idColaborador;
	private LocalDate dataEmissao;
	// Folha Normal
	private double valorHorasTrabalhadas;
	private double valorHorasFaltas;
	private double valorHorasExtras;
	private double valorReflexoDSR;
	private double valorInss;
	private double valorImpostoDeRenda;
	private double valorPlanoSaude;
	private double valorValeTransporte;
	private double salarioBruto;
	private double salarioLiquido;
	private double valorFGTS;
	private double valorFerias;
	private double valorInssFerias;
	private double valorImpostoDeRendaFerias;
	private double feriasLiquido;
	private ICalculoHoras calculoHoras;
	private ICalculoDesconto calculoDesconto;
	private double valorHora;
	private double valorPlr;

	// Variavel referente ao valor da bonificacao
	private double bonificacao;

	/**
	 * Constoi a folha com os dados calculados.
	 * 
	 * Constroi uma folha{@link Folha} e determina a data de emissao da mesma.
	 * 
	 * @author Lucas Grijo
	 * @author Lucas Walim
	 * @author Marcelo Schaefer
	 *
	 *         Sprint 5:
	 * @author Leonardo Felipe Silva <felipeleao217@gmail.com>;
	 * @author Bruna Carvalho <sh4323202@gmail.com>;
	 * @author Leonardo Pereira <leonardopereirajr@gmail.com>;
	 * @author Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
	 * @author Lucas Nunes <lucasnunes.ln365@gmail.com>.
	 *
	 */
	public Folha build() {
		return new Folha(id, idColaborador, dataEmissao, valorHorasTrabalhadas, valorHorasFaltas, valorHorasExtras,
				valorReflexoDSR, valorInss, valorImpostoDeRenda, valorPlanoSaude, valorValeTransporte, salarioBruto,
				salarioLiquido, valorFerias, valorInssFerias, valorImpostoDeRendaFerias, feriasLiquido, valorFGTS,
				valorPlr, bonificacao);
	}

	/**
	 * Inicializa Calculos. Realiza os calculos inicias e instancia os objetos
	 * necessarios para qualquer calculo necessario para cada tipo de folha. Realiza
	 * os calculos inicias e instancia os objetos necess�rios para o calculos de
	 * qualquer tipo de folha.
	 * 
	 * @author Lucas Grijo
	 * @author Lucas Walim
	 * @author Marcelo Schaefer
	 */
	public void iniciarCalculos(IColaboradorFolha colaborador, ICargoFolha cargo) {
		idColaborador = colaborador.getId();
		this.calculoHoras = new CalculoHoras();
		this.calculoDesconto = new CalculoDesconto();
		valorHora = (calculoHoras.calcularValorHora(cargo));
	}
	
	/**
	 * Atribui a data atual do sistema para a {@link Folha} a ser construida.
	 */
	public void setDataEmissaoFolha() {
		dataEmissao = LocalDate.now();
	}

	/**
	 * Calculo das Horas Normais.
	 * 
	 * Realiza os calculos do valor recebido pertinente a horas trabalhadas, faltas,
	 * horas extras e reflexo DSR.
	 * 
	 * @author Lucas Grij�
	 * @author Lucas Walim
	 * @author Marcelo Schaefer
	 */
	public void calcularHorasNormais(IPontoFolha ponto, ICargoFolha cargo) {
		valorHorasTrabalhadas = (calculoHoras.calcularValorDasHorasTrabalhadas(ponto, valorHora));
		valorHorasFaltas = (calculoHoras.calcularValorHorasFaltas(ponto, valorHora));
		valorHorasExtras = (calculoHoras.calcularValorHorasExtras(ponto, valorHora));
		valorReflexoDSR = (calculoHoras.calcularDSR(valorHorasExtras));
		salarioBruto = (valorHorasTrabalhadas - valorHorasFaltas + valorHorasExtras + valorReflexoDSR);
		valorFGTS = (valorFGTS * salarioBruto);	
	}
	
	/**
	 * Calcula o valor do {@link Plr}
	 * 
	 * Busca no banco de dados o {@link Plr} cadastrado no m�s e ano da data de emissao da {@link Folha}
	 * e associa o valor do respectivo {@link Plr} � {@link Folha}.
	 * 
	 * @return void
	 */
	public void calcularPlr(int valor) {
		PlrController plrController = new PlrController();
		
		this.valorPlr = plrController.getValorPlrMes(dataEmissao);
	}

	/**
	 * Calculo de descontos em folha normal.
	 * 
	 * Atribui descontos no salario pertinente a inss, imposto de renda, plano de
	 * saude e vale transporte. Tambem determina o sal�rio liquido.
	 * 
	 * @author Lucas Grij�
	 * @author Lucas Walim
	 * @author Marcelo Schaefer
	 */
	public void calcularDescontoNormal(IColaboradorFolha colaborador, ICargoFolha cargo) {
		valorInss = (calculoDesconto.calcularDescontoInss(salarioBruto));
		salarioLiquido = ((salarioBruto) - valorInss);
		valorImpostoDeRenda = (calculoDesconto.calcularDescontoImpostoRenda(colaborador, salarioLiquido));
		valorPlanoSaude = (calculoDesconto.calcularDescontoPlanoSaude(colaborador));
		valorValeTransporte = (calculoDesconto.calcularDescontoValeTransporte(colaborador, cargo));
		salarioLiquido = (salarioLiquido - valorValeTransporte - valorImpostoDeRenda - valorPlanoSaude) + valorPlr;
		valorFGTS = (calculoDesconto.calcularFGTS(salarioBruto));
	}

	/**
	 * Calculo das horas ferias.
	 * 
	 * Realiza os calculo do valor recebido pertinente a horas de ferias.
	 * 
	 * @author Lucas Grijo
	 * @author Lucas Walim
	 * @author Marcelo Schaefer
	 */
	public void calcularHorasFerias(IFeriasFolha feriasFolha) {
		this.valorFerias = (calculoHoras.calcularFerias(feriasFolha.getDias(), feriasFolha.getAbono(), valorHora));
	}

	/**
	 * Calculo de descontos em folha ferias.
	 * 
	 * Atribui descontos no valor das ferias pertinente a inss e imposto de renda.
	 * Tambem determina o valor de ferias liquido.
	 * 
	 * @author Lucas Grijo
	 * @author Lucas Walim
	 * @author Marcelo Schaefer
	 */
	public void calcularDescontoFerias(IColaboradorFolha colaborador) {
		valorInssFerias = (calculoDesconto.calcularDescontoInss(valorFerias));
		feriasLiquido = (valorFerias - valorInssFerias);
		valorImpostoDeRendaFerias = (calculoDesconto.calcularDescontoImpostoRenda(colaborador, feriasLiquido));
		feriasLiquido = (feriasLiquido - valorImpostoDeRendaFerias);
	}

	/**
	 * Altera a bonifica��o por colaborador atribuindo ao salarioBase o valor da
	 * bonificacao.
	 * 
	 * @param cargo,       referente ao {@link ColaboradorFolha}
	 * @param bonificacao, referente ao valor da bonificacao a ser atribuido ao
	 *                     sal�rio base
	 */
	public double atribuiBonificacaoColaboradorCargo(ICargoFolha cargo, Bonificacao bonificacao) {
		this.bonificacao = cargo.getSalarioBase() * bonificacao.getPorcentagemBonificacaoColaborador();
		return this.salarioBruto += this.bonificacao;
	}

	

}