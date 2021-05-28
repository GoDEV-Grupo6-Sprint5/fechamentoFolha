package br.com.proway.senior.model;

import java.time.LocalDate;

/**
 * Interface IPlr.
 * 
 * Metodos get e set implementados pela classe {@link Plr}.
 * 
 * @author Lucas Grij�
 * @author Lucas Walim
 * @author Marcelo Schaefer
 * 
 * Sprint 5:
 * @author Leonardo Felipe Silva <felipeleao217@gmail.com>;
 * @author Bruna Carvalho <sh4323202@gmail.com>;
 * @author Leonardo Pereira <leonardopereirajr@gmail.com>;
 * @author Sabrina Schmidt <sabrinaschmidt335@gmail.com>;
 * @author Lucas Nunes <lucasnunes.ln365@gmail.com>.
 *
 */
public interface IPlr {
	
	public double getValorPorcentagemPlr();

	public void setValorPorcentagemPlr(double valorPlr);

	public LocalDate getVencimento();

	public void setVencimento(LocalDate vencimento);

	public int getId();
}
