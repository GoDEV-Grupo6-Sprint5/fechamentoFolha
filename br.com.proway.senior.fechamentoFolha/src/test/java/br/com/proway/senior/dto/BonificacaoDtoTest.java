package br.com.proway.senior.dto;

import static org.junit.Assert.*;


import org.junit.Test;

import br.com.proway.senior.model.Bonificacao;

public class BonificacaoDtoTest {
	
		@Test
		public void testeGetPorcentagem() {
			Bonificacao bonificacao = new Bonificacao();
			bonificacao.setPorcentagemBonificacaoColaborador(2.0);
			BonificacaoDto bonificacaoDto = new BonificacaoDto(bonificacao);
			assertNotNull(bonificacaoDto.getPorcentagemBonificacaoColaborador());
		}
	}
