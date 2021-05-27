package br.com.proway.senior.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.dto.FolhaDto;
import br.com.proway.senior.model.Bonificacao;
import br.com.proway.senior.model.Folha;
import br.com.proway.senior.model.externo.interfaces.ICargoFolha;
import br.com.proway.senior.model.externo.interfaces.IColaboradorFolha;
import br.com.proway.senior.model.externo.interfaces.IFeriasFolha;
import br.com.proway.senior.model.externo.interfaces.IPontoFolha;

@RestController
@RequestMapping(value = "/api/v1/folha", produces = "application/json")

public class FolhaControllerAPI {
	@Autowired
	private FolhaController folhaController;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<FolhaDto>> buscarTodasFolhas() {
		List<FolhaDto> listaFolhaDTO = new ArrayList<FolhaDto>();
		List<Folha> lista = folhaController.getAll();
		for (Folha f : lista) {
			listaFolhaDTO.add(new FolhaDto(f));
		}
		return ResponseEntity.ok(listaFolhaDTO);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity <FolhaDto> buscarFolhasPorId(@PathVariable("id") Integer id) {
		FolhaDto folha = new FolhaDto(folhaController.getById(id));
		return ResponseEntity.ok(folha);
	}

	@PostMapping
	public ResponseEntity<FolhaDto> construirFolhaNormal(IColaboradorFolha colaborador, IPontoFolha ponto, ICargoFolha cargo,
			Bonificacao bonificacao) {
		FolhaDto folhaNormal = new FolhaDto(
				folhaController.construirFolhaNormal(colaborador, ponto, cargo, bonificacao));
		return ResponseEntity.ok(folhaNormal);
	}

	/*
	 * @PostMapping public ResponseEntity <FolhaDto>
	 * construirFolhaFerias(IColaboradorFolha colaborador, IFeriasFolha ferias,
	 * ICargoFolha cargo) { FolhaDto folhaFerias = new
	 * FolhaDto(folhaController.construirFolhaFerias(colaborador, ferias, cargo));
	 * return ResponseEntity.ok(folhaFerias); }
	 * 
	 * @PostMapping public ResponseEntity <FolhaDto>
	 * construirFolhaHibrida(IColaboradorFolha colaborador, IPontoFolha ponto,
	 * ICargoFolha cargo, IFeriasFolha ferias) { FolhaDto folhaHibrida = new
	 * FolhaDto(folhaController.construirFolhaHibrida(colaborador, ponto, cargo,
	 * ferias)); return ResponseEntity.ok(folhaHibrida); }
	 */

}
