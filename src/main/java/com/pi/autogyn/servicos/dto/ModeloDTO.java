package com.pi.autogyn.servicos.dto;

import com.pi.autogyn.persistencia.entidades.Modelo;

public class ModeloDTO {
	private Long id;
	private String nome;
	
	public ModeloDTO() {	
		
	}
	
	public ModeloDTO(Modelo modelo) {
		this.id = modelo.getId();
		this.nome = modelo.getNome();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
}
