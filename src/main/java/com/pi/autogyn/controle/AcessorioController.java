package com.pi.autogyn.controle;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.pi.autogyn.persistencia.dao.AcessorioDao;
import com.pi.autogyn.persistencia.dao.ServicoDao;
import com.pi.autogyn.servicos.dto.AcessorioService;
import com.pi.autogyn.servicos.dto.CadastrarAcessorioDTO;
import com.pi.autogyn.validacoes.MensagemErro;

@Controller
public class AcessorioController {
	
	@PostMapping("/acessorio")
	public ResponseEntity<?> criarAcessorio(CadastrarAcessorioDTO novoAcessorio) throws SQLException {
		if (novoAcessorio == null) {
			return ResponseEntity.badRequest().body(new MensagemErro("Envie o campo descricao."));
		}
		boolean criado = AcessorioService.criarProduto(novoAcessorio.getDescricao());
		if (criado) {
			return ResponseEntity.ok("Acessorio criado com sucesso.");
		} else {
			return ResponseEntity.badRequest().body(new MensagemErro("Já existe um acessorio com este nome"));
		}
	}
	
}
