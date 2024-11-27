package com.pi.autogyn.controle;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pi.autogyn.servicos.ServicoService;
import com.pi.autogyn.servicos.dto.CadastrarServicoDTO;

@Controller
public class ServicoController {
	@PostMapping("/servico")
	public ResponseEntity<String> criarServico(CadastrarServicoDTO servico) {
		if (servico.getDescricao() == null || servico.getValor() == null) {
			return ResponseEntity.badRequest().body("Campos obrigatórios não estão presentes: ['descricao' ou 'valor']")
		}
		try {
			boolean criado = ServicoService.cadastrarServico(servico);
			if (criado) {
				return ResponseEntity.ok("Servico cadastrado com sucesso.")
			} else {
				return ResponseEntity.badRequest().body("Nome já foi utilizado anteriormente");
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
