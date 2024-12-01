package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pi.autogyn.servicos.OSService;
import com.pi.autogyn.servicos.dto.CadastrarClienteDTO;
import com.pi.autogyn.servicos.dto.CadastrarOSDTO;
import com.pi.autogyn.servicos.dto.OrdemServicoListaDTO;
import com.pi.autogyn.servicos.dto.VeiculoListaCadastradosDto;

@Controller
public class OSController {
	
	@GetMapping("/os/lista-cadastrados")
	public ResponseEntity<List<OrdemServicoListaDTO>> listaOs() throws SQLException {
		return ResponseEntity.ok(OSService.listarOSCadastradas());
	}
	
	@PostMapping("/os")
	public ResponseEntity<?> criarOs(CadastrarOSDTO novaOS) {
		OSService.criarOS(novaOS);
		return null;
	}
	
}
