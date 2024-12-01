package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.pi.autogyn.persistencia.entidades.Modelo;
import com.pi.autogyn.servicos.VeiculoService;
import com.pi.autogyn.servicos.dto.AtualizarVeiculoDTO;
import com.pi.autogyn.servicos.dto.CadastrarMarcaDTO;
import com.pi.autogyn.servicos.dto.CadastrarVeiculoDTO;
import com.pi.autogyn.servicos.dto.MarcaListaCadastroDTO;
import com.pi.autogyn.servicos.dto.MinimalAcessorioDTO;
import com.pi.autogyn.servicos.dto.MinimalMarcaDTO;
import com.pi.autogyn.servicos.dto.ModeloDTO;
import com.pi.autogyn.servicos.dto.NovoModeloDTO;
import com.pi.autogyn.servicos.dto.VeiculoListaCadastradosDto;

@Controller
public class VeiculosController {
	
	@PostMapping("/veiculo")
	public ResponseEntity<?> inserirVeiculo(CadastrarVeiculoDTO novoVeiculo) throws SQLException {
		String statusCadastro = VeiculoService.insertVeiculo(novoVeiculo);
		if (statusCadastro != null) {
			return ResponseEntity.badRequest().body(statusCadastro);
		}

		return ResponseEntity.ok("Veículo Criado com Sucesos");
	}
	
	@PostMapping("/veiculo/{placa}")
	public ResponseEntity<?> atualizarVeiculo(@PathVariable String placa, AtualizarVeiculoDTO atualizarVeiculo) throws SQLException {
		String status = VeiculoService.atualizarVeiculo(placa, atualizarVeiculo);
		if (atualizarVeiculo.getId_novo_proprietario() == null && atualizarVeiculo.getQuilometragem() == null) {
			return ResponseEntity.badRequest().body("Nenhum parametro foi recebido");
		}
		if (status != null) {
			return ResponseEntity.badRequest().body(status);
		}
		return ResponseEntity.ok("Operação realizada");
	}
	
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
	
	@GetMapping("/veiculo/marca/{idMarca}")
	public ResponseEntity<?> getModelosDaMarca(@PathVariable Long idMarca) throws SQLException {
	    List<ModeloDTO> modelos = VeiculoService.listarModelosPorMarca(idMarca);
	    if (modelos != null) {
	    	return ResponseEntity.ok(modelos);
	    } else {
	    	return ResponseEntity.status(404).body("Marca nao cadastrada.");
	    }
	}	
	
	@GetMapping("/veiculo/acessorio")
	public ResponseEntity<List<MinimalAcessorioDTO>> getAcessorios() throws SQLException {
		return ResponseEntity.ok(VeiculoService.listarMinimalAcessorios());
	}
	
	@PostMapping("/veiculo/marca")
	public ResponseEntity<String> cadastrarMarca(@RequestBody CadastrarMarcaDTO marca) {
	   try {
		   VeiculoService.insertMarca(marca.getMarca());
	       return ResponseEntity.ok("Marca cadastrada com sucesso");
	   } catch (Exception e) {
	       return ResponseEntity.badRequest().body("Erro ao cadastrar marca: " + e.getMessage());
	   }
	}
	
	@PostMapping("/veiculo/marca/modelo")
	public ResponseEntity<String> cadastrarModelo(@RequestBody NovoModeloDTO novoModelo) {
		try {
				VeiculoService.insertModelo(novoModelo);
		       return ResponseEntity.ok("Modelo cadastrado com sucesso");
		   } catch (Exception e) {
		       return ResponseEntity.badRequest().body("Erro ao cadastrar modelo: " + e.getMessage());
		   }
	}
	
}
