package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PatchExchange;

import com.pi.autogyn.persistencia.dao.OSDao;
import com.pi.autogyn.persistencia.dao.ServicoDao;
import com.pi.autogyn.servicos.OSService;
import com.pi.autogyn.servicos.dto.CadastrarClienteDTO;
import com.pi.autogyn.servicos.dto.CadastrarOSDTO;
import com.pi.autogyn.servicos.dto.OSDTO;
import com.pi.autogyn.servicos.dto.OrdemServicoListaDTO;
import com.pi.autogyn.servicos.dto.VeiculoListaCadastradosDto;
import com.pi.autogyn.validacoes.GatewayValidacao;
import com.pi.autogyn.validacoes.MensagemErro;
import com.pi.autogyn.validacoes.StatusValidacao;


@Controller
public class OSController {
	
	private GatewayValidacao validacao = new GatewayValidacao();
	
	@GetMapping("/os/lista-cadastrados")
	public ResponseEntity<List<OrdemServicoListaDTO>> listaOs() throws SQLException {
		return ResponseEntity.ok(OSService.listarOSCadastradas());
	}
	
	@GetMapping("/os/{id}")
	public ResponseEntity<OSDTO> getOs(@PathVariable Long id) throws SQLException {
		return ResponseEntity.ok(OSService.getOs(id));
	}
	
	@PostMapping("/os/{id}/aprovar")
	public ResponseEntity<?> aprovarOs(@PathVariable Long id) throws SQLException {
		String status = OSService.aprovarOS(id);
		if (status != null) {
			return ResponseEntity.badRequest().body(status);
		}
		return ResponseEntity.ok("Status mudado para aprovado");
	}
	
	@PostMapping("/os/{id}/iniciar")
	public ResponseEntity<?> iniciarOS(@PathVariable Long id) throws SQLException {
		String status = OSService.iniciarExecucaoOS(id);
		if (status != null) {
			return ResponseEntity.badRequest().body(status);
		}
		return ResponseEntity.ok("Status mudado para em execução");
	}
	
	@PostMapping("/os/{idOs}/servico/{idServico}/status")
	public ResponseEntity<?> completarServico(@PathVariable Long idOs, @PathVariable Long idServico,  @RequestBody Map<String, Boolean> request) throws SQLException {
		if (!request.containsKey("terminado")) {
			return ResponseEntity.badRequest().body("Mande o atributo 'terminado'");
		}
		boolean terminar = request.get("terminado");
		String status = OSDao.completarServico(idOs, idServico, terminar);
		if (status != null) {
			return ResponseEntity.badRequest().body(status);
		}
		return ResponseEntity.ok("Status alterado");
	}
	
	@PostMapping("/os")
	public ResponseEntity<?> criarOs(CadastrarOSDTO novaOS) throws SQLException {
		List<StatusValidacao> erros = validacao.validar(novaOS);
		if (erros.size() > 0) {
			return ResponseEntity.badRequest().body(new MensagemErro(erros));
		}
		Long idOS= OSService.criarOS(novaOS);
		System.out.println(idOS);
		return ResponseEntity.ok(OSDao.getById(idOS).getValor_total());
	}
	
}
