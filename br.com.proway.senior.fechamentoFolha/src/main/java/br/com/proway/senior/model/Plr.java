package br.com.proway.senior.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Valor do Plr.
 * 
 * Objeto que implementa de {@link IPlr} e seta um valor fixo para o plr.
 * 
 * Sprint 5:
 * 
 * @author Leonardo Felipe Silva <felipeleao217@gmail.com>;
 * @author Bruna Carvalho <sh4323202@gmail.com>;
 * @author Leonardo Pereira <leonardopereirajr@gmail.com>;
 * @author Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
 * @author Lucas Nunes <lucasnunes.ln365@gmail.com>.
 */

@Entity
public class Plr implements IPlr {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLR_SEQ")
	@SequenceGenerator(name = "PLR_SEQ", sequenceName = "PLR_SEQ", allocationSize = 1)
	private int id;

	private double valorPlr;
	private LocalDate vencimento;

	public double getValorPorcentagemPlr() {
		return valorPlr;
	}
	
	//Metodo resposavel por setar o percentual do plr.  
	public void setValorPorcentagemPlr(double valorPlr) {
		this.valorPlr = (valorPlr / 100) * 1000.0;
	}

	public LocalDate getVencimento() {
		return vencimento;
	}

	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}

	public int getId() {
		return id;
	}

}