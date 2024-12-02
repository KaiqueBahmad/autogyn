package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pi.autogyn.servicos.ServicoService;
import com.pi.autogyn.servicos.dto.CadastrarServicoDTO;
import com.pi.autogyn.servicos.dto.ServicoFormatadoDTO;
import com.pi.autogyn.validacoes.MensagemErro;

@Controller
public class ServicoController {
	@PostMapping("/servico")
	public ResponseEntity<?> criarServico(CadastrarServicoDTO servico) {
		if (servico.getDescricao() == null || servico.getValor() == null) {
			return ResponseEntity.badRequest().body(
				new MensagemErro("Campos obrigatórios não estão presentes: ['descricao' ou 'valor']")
			);
		}
		try {
			boolean criado = ServicoService.cadastrarServico(servico);
			if (criado) {
				return ResponseEntity.ok("Servico cadastrado com sucesso.");
			} else {
				return ResponseEntity.badRequest().body(new MensagemErro("Nome já foi utilizado anteriormente"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/servico/formatado")
	public ResponseEntity<List<ServicoFormatadoDTO>> getServicosFormatados() throws SQLException {
		return ResponseEntity.ok(ServicoService.buscarServicosFormatados());
	}
	
	

	
	
	
	
	
}
