package br.com.proway.senior.controller;

import br.com.proway.senior.dto.FolhaDto;
import br.com.proway.senior.model.Bonificacao;
import br.com.proway.senior.model.Folha;
import br.com.proway.senior.model.externo.CargoFolha;
import br.com.proway.senior.model.externo.ColaboradorFolha;
import br.com.proway.senior.model.externo.PontoFolha;
import br.com.proway.senior.model.externo.interfaces.ICargoFolha;
import br.com.proway.senior.model.externo.interfaces.IColaboradorFolha;
import br.com.proway.senior.model.externo.interfaces.IPontoFolha;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FolhaControllerAPITest {

    @Mock
    private FolhaController mockFolhaController;

    @InjectMocks
    private FolhaControllerAPI folhaControllerAPIUnderTest;

    @Test
    public void testBuscarTodasFolhas() {
        // Setup

        // Configure FolhaController.getAll(...).
        final List<Folha> folhas = Arrays.asList(new Folha(0, 0, LocalDate.of(2020, 1, 1), 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        when(mockFolhaController.getAll()).thenReturn(folhas);

        // Run the test
        final ResponseEntity<List<FolhaDto>> result = folhaControllerAPIUnderTest.buscarTodasFolhas();

        // Verify the results
    }

    @Test
    public void testBuscarTodasFolhas_FolhaControllerReturnsNoItems() {
        // Setup
        when(mockFolhaController.getAll()).thenReturn(Collections.emptyList());

        // Run the test
        final ResponseEntity<List<FolhaDto>> result = folhaControllerAPIUnderTest.buscarTodasFolhas();

        // Verify the results
    }

    @Test
    public void testBuscarFolhasPorId() {
        // Setup

        // Configure FolhaController.getById(...).
        final Folha folha = new Folha(0, 0, LocalDate.of(2020, 1, 1), 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        when(mockFolhaController.getById(0)).thenReturn(folha);

        // Run the test
        final ResponseEntity<FolhaDto> result = folhaControllerAPIUnderTest.buscarFolhasPorId(0);

        // Verify the results
    }

    @Test
    public void testConstruirFolhaNormal() {
        // Setup
        final IColaboradorFolha colaborador = new ColaboradorFolha(0, true, 0.0
                , 0.0, 0.0);
        final IPontoFolha ponto = new PontoFolha(300.00, 80.00, 0.0);
        final ICargoFolha cargo = new CargoFolha(2500.00, 0.20);
        final Bonificacao bonificacao = new Bonificacao();
        bonificacao.setPorcentagemBonificacaoColaborador(0.0);
        bonificacao.setId(0);

        // Configure FolhaController.construirFolhaNormal(...).
        final Folha folha = new Folha(0, 0, LocalDate.of(2020, 1, 1), 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        when(mockFolhaController.construirFolhaNormal(any(IColaboradorFolha.class), any(IPontoFolha.class), any(ICargoFolha.class), any(Bonificacao.class))).thenReturn(folha);

        // Run the test
        final ResponseEntity<FolhaDto> result = folhaControllerAPIUnderTest.construirFolhaNormal(colaborador, ponto, cargo, bonificacao);

        // Verify the results
        Assert.assertNotNull(result);
    }
}
