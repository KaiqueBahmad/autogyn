package com.pi.autogyn.validacoes;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.pi.autogyn.servicos.dto.CadastrarAcessorioDTO;
import com.pi.autogyn.servicos.dto.CadastrarClienteDTO;
import com.pi.autogyn.servicos.dto.NovoModeloDTO;

public class GatewayValidacao {
	private Validador validador;

	public GatewayValidacao() {
		this.validador = new Validador();
	}

	public List<StatusValidacao> validar(CadastrarClienteDTO novoCliente) {
		List<StatusValidacao> erros = new LinkedList<>();
		
		
		if (novoCliente.isPJ()) {
			erros.add(this.validador.validaCNPJ(novoCliente.getCnpj()));
			erros.add(this.validador.validaInscricaoEstadual(novoCliente.getInscricao_estadual()));
		}
		if (!novoCliente.isPJ()) {
		erros.add(this.validador.validaCPF(novoCliente.getCpf()));	
		}
		
		erros.add(this.validador.validaEmail(novoCliente.getEmail()));
		erros.add(this.validador.validaCEP(novoCliente.getCep()));
		erros.add(this.validador.validaUF(novoCliente.getUf()));
		erros.add(this.validador.validaTelefone(novoCliente.getTelefone()));
		erros.add(this.validador.validaDDD(novoCliente.getDdd()));
		if (novoCliente.getDdd2() != null) {
			erros.add(this.validador.validaDDD(novoCliente.getDdd2()));
			erros.add(this.validador.validaTelefone(novoCliente.getTelefone2()));
		}
		removerNulos(erros);
		
		return erros;
	}

	public List<StatusValidacao> validar(CadastrarAcessorioDTO novoAcessorio) {
		List<StatusValidacao> erros = new LinkedList<>();
		return erros;
	}

	private static void removerNulos(List<StatusValidacao> lista) {
		// remover todos os nulos
		lista.removeIf(Objects::isNull);
	}
	
	

}
