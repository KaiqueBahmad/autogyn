package com.pi.autogyn.servicos.dto;

import com.pi.autogyn.persistencia.entidades.Cliente;
import com.pi.autogyn.persistencia.entidades.PF;
import com.pi.autogyn.persistencia.entidades.PJ;

public class ClienteDTO {
	private Long id;
	private String nomeFormatado;
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		if (cliente.getPessoaFisica().isPresent()) {
			PF pf = cliente.getPessoaFisica().get();
			this.nomeFormatado = String.format("[%s] | %s", pf.getCpf(), cliente.getNome());
		} else if (cliente.getPessoaJuridica().isPresent()) {
			PJ pj = cliente.getPessoaJuridica().get();
			this.nomeFormatado = String.format("[%s] | %s", pj.getCnpj(), cliente.getNome());
		}
		
	}
	
	public ClienteDTO() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFormatado() {
		return nomeFormatado;
	}

	public void setNomeFormatado(String nomeFormatado) {
		this.nomeFormatado = nomeFormatado;
	}
	
	

}
