package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pi.autogyn.servicos.ColaboradorService;
import com.pi.autogyn.servicos.dto.CadastrarColaboradorDTO;
import com.pi.autogyn.servicos.dto.ColaboradorDTO;

@Controller
public class ColaboradorController {
	
	@GetMapping("/colaborador")
	public ResponseEntity<List<ColaboradorDTO>> todosColaboradores() throws SQLException {
		return ResponseEntity.ok(ColaboradorService.listarTodos());
	}
	
	@PostMapping("/colaborador")
	public ResponseEntity<?> novoColaborador(CadastrarColaboradorDTO novoColaboradorDTO) throws SQLException {
		String status = ColaboradorService.incluirColaborador(novoColaboradorDTO);
		if (status == null) {
			return ResponseEntity.ok("Colaborador Criado com sucesso.");
		}
		return ResponseEntity.badRequest().body(status);
	}
}
