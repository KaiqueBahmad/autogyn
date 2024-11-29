package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pi.autogyn.persistencia.entidades.Peca;
import com.pi.autogyn.servicos.PecaService;
import com.pi.autogyn.servicos.dto.AtualizarPecaDTO;
import com.pi.autogyn.servicos.dto.CadastrarPecaDTO;
import com.pi.autogyn.servicos.dto.PecaListaDTO;
import com.pi.autogyn.validacoes.MensagemErro;

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
	
	@PostMapping("/peca/{idPeca}/add")
	public ResponseEntity<?> adicionarEstoque(@PathVariable Long idPeca, @RequestParam Integer quantidade) throws SQLException {
		Peca peca = PecaService.findById(idPeca);
		if (peca == null) {
			return ResponseEntity.notFound().build();
		}
		if (quantidade == null) {
			return ResponseEntity.badRequest().body(new MensagemErro("Envie o parametro 'quantidade'"));
		}
		if (peca.getQuantidadeEstoque() + quantidade < 0) {
			return ResponseEntity.badRequest().body("Não é possível abaixar o estoque para menos que zero.");
		}
		boolean rodou = PecaService.addNoEstoque(idPeca, quantidade);
		if (!rodou) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok("Quantidade alterada com sucesso.");
		
	}
	
	@PatchMapping("/peca/{idPeca}")
	public ResponseEntity<?> atualizarPeca(@PathVariable Long idPeca, AtualizarPecaDTO atualizarPeca) throws SQLException {
		String status = PecaService.atualizarPeca(idPeca, atualizarPeca);
		if (status == null) {
			return ResponseEntity.ok("Peça atualizada com sucesso");
		}
		return ResponseEntity.badRequest().body(status);
	}
	
	
}
