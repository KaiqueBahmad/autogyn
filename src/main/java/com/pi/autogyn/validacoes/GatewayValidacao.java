package com.pi.autogyn.validacoes;

import com.pi.autogyn.servicos.dto.CadastrarAcessorioDTO;
import com.pi.autogyn.servicos.dto.CadastrarClienteDTO;

public class GatewayValidacao {
	private Validador validador;
	
	public GatewayValidacao() {
		this.validador = new Validador();
	}
	
	public StatusValidacao validar(CadastrarClienteDTO novoCliente) {
		return StatusValidacao.OK;
	}
	
	public StatusValidacao validar(CadastrarAcessorioDTO novoAcessorio) {
		return StatusValidacao.OK;
	}
	
	
	
	
}
