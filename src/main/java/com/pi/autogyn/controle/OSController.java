package com.pi.autogyn.controle;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pi.autogyn.persistencia.dao.OSDao;
import com.pi.autogyn.servicos.OSService;
import com.pi.autogyn.servicos.dto.CadastrarClienteDTO;
import com.pi.autogyn.servicos.dto.CadastrarOSDTO;
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
