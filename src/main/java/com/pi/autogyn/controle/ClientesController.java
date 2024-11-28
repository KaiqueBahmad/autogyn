package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pi.autogyn.servicos.ClienteService;
import com.pi.autogyn.servicos.dto.CadastrarClienteDTO;
import com.pi.autogyn.servicos.dto.ClienteDTO;

@Controller
public class ClientesController {

	@GetMapping("/cliente")
	public ResponseEntity<List<ClienteDTO>> listarClientes() throws SQLException {
		return ResponseEntity.ok(ClienteService.listarTodosFormatados());
	}
	
	
	@PostMapping("/cliente")
	public ResponseEntity<String> cadastrarCliente(@RequestBody CadastrarClienteDTO novoCliente) throws SQLException {
		Long idCliente = ClienteService.inserirCliente(novoCliente);
		if (idCliente == null) {
			return ResponseEntity.badRequest().body("Cliente não criado.");
		}
		return ResponseEntity.ok("Criado Cliente "+idCliente);
	}
	
}
