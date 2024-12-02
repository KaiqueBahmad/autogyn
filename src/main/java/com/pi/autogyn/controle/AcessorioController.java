package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.pi.autogyn.persistencia.dao.AcessorioDao;
import com.pi.autogyn.persistencia.dao.ServicoDao;
import com.pi.autogyn.servicos.dto.AcessorioService;
import com.pi.autogyn.servicos.dto.CadastrarAcessorioDTO;
import com.pi.autogyn.validacoes.GatewayValidacao;
import com.pi.autogyn.validacoes.MensagemErro;
import com.pi.autogyn.validacoes.StatusValidacao;

@Controller
public class AcessorioController {
	
	private static GatewayValidacao validador = new GatewayValidacao();
	
	@PostMapping("/acessorio")
	public ResponseEntity<?> criarAcessorio(CadastrarAcessorioDTO novoAcessorio) throws SQLException {
		List<StatusValidacao> erros = validador.validar(novoAcessorio);
		if (erros.size() > 0) {
			return ResponseEntity.badRequest().body(new MensagemErro(erros));
		}
		if (novoAcessorio == null) {
			return ResponseEntity.badRequest().body(new MensagemErro("Envie o campo descricao."));
		}
		boolean criado = AcessorioService.criarProduto(novoAcessorio.getDescricao());
		if (criado) {
			return ResponseEntity.ok(new MensagemErro("Acessorio criado com sucesso."));
		} else {
			return ResponseEntity.badRequest().body(new MensagemErro("Já existe um acessorio com este nome"));
		}
	}
	
}
