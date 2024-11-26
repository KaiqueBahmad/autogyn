package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pi.autogyn.servicos.VeiculoService;
import com.pi.autogyn.servicos.dto.MarcaListaCadastroDTO;
import com.pi.autogyn.servicos.dto.MinimalMarcaDTO;
import com.pi.autogyn.servicos.dto.VeiculoListaCadastradosDto;

@Controller
public class VeiculosController {
	
	@GetMapping("/veiculo/lista-cadastro")
	public ResponseEntity<List<VeiculoListaCadastradosDto>> veiculosCadastrados() throws SQLException {
		return ResponseEntity.ok(VeiculoService.listarVeiculosCadastrados());
	}
	
	@GetMapping("/veiculo/marca/lista-cadastro")
	public ResponseEntity<List<MarcaListaCadastroDTO>> marcasCadastradas() throws SQLException {
		return ResponseEntity.ok(VeiculoService.listarMarcasCadastradas());
	}
	
	
	@GetMapping("/veiculo/marca")
	public ResponseEntity<List<MinimalMarcaDTO>> getMarcas() throws SQLException {
		return ResponseEntity.ok(VeiculoService.listarMinimalMarcas());
	}
	
	@PostMapping("/veiculo/marca")
	public ResponseEntity<String> cadastrarMarca(@RequestBody String nome) {
	   try {
		   VeiculoService.insertMarca(nome);
	       return ResponseEntity.ok("Marca cadastrada com sucesso");
	   } catch (Exception e) {
	       return ResponseEntity.badRequest().body("Erro ao cadastrar marca: " + e.getMessage());
	   }
	}
	
	@PostMapping("/veiculo/marca/modelo")
	public ResponseEntity<String> cadastrarModelo(@RequestBody Long idMarca, @RequestBody String nomeModelo) {
		try {
				VeiculoService.insertModelo(idMarca, nomeModelo);
		       return ResponseEntity.ok("Modelo cadastrado com sucesso");
		   } catch (Exception e) {
		       return ResponseEntity.badRequest().body("Erro ao cadastrar modelo: " + e.getMessage());
		   }
	}
	
}
