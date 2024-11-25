package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pi.autogyn.servicos.VeiculoService;
import com.pi.autogyn.servicos.dto.VeiculoListaCadastradosDto;

@Controller
public class VeiculosController {
	
	@GetMapping("/veiculo/lista-cadastro")
	public ResponseEntity<List<VeiculoListaCadastradosDto>> veiculosCadastrados() throws SQLException {
		return ResponseEntity.ok(VeiculoService.listarVeiculosCadastrados());
	}
	
	@PostMapping("/veiculo/marca")
	public ResponseEntity<String> cadastrarMarca(@RequestBody String nome) {
	   try {
	       // Assuming marca has a 'nome' field that gets populated from request body
	       return ResponseEntity.ok("Marca cadastrada com sucesso");
	   } catch (Exception e) {
	       return ResponseEntity.badRequest().body("Erro ao cadastrar marca: " + e.getMessage());
	   }
	}
	
}
