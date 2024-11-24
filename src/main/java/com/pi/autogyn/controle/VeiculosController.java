package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pi.autogyn.servicos.VeiculoService;
import com.pi.autogyn.servicos.dto.VeiculoListaCadastroDto;

@Controller
public class VeiculosController {
	
	@GetMapping("/veiculo/lista-cadastro")
	public ResponseEntity<List<VeiculoListaCadastroDto>> teste() throws SQLException {
		return ResponseEntity.ok(VeiculoService.listarVeiculosCadastrados());
	}
}
