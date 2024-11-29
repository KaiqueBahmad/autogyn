package com.pi.autogyn.servicos.dto;

import com.pi.autogyn.persistencia.entidades.Colaborador;

public class CadastrarColaboradorDTO {
	private String cpf;
	private String nome;
	
	public CadastrarColaboradorDTO() {
			
		}
	public CadastrarColaboradorDTO(Colaborador colaborador) {
		this.cpf = colaborador.getCpf();
		this.nome = colaborador.getNome();
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
