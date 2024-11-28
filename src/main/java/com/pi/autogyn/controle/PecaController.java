package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pi.autogyn.servicos.dto.CadastrarPecaDTO;
import com.pi.autogyn.servicos.dto.PecaListaDTO;

@Controller
public class PecaController {
	@PostMapping("/peca")
	public ResponseEntity<String> cadastrarPeca(CadastrarPecaDTO novaPeca) throws SQLException {
		return ResponseEntity.ok(PecaService.cadastrarPeca(novaPeca));
	}
	
	@GetMapping("/peca")
	public ResponseEntity<List<PecaListaDTO>> listarPecas() throws SQLException {
		return ResponseEntity.ok(PecaService.listarPecas());
	}
	
}
