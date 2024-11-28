package com.pi.autogyn.servicos.dto;

import com.pi.autogyn.persistencia.entidades.Marca;

public class MinimalMarcaDTO {
	private Long id;
	private String nome;
	
	public MinimalMarcaDTO(Marca marca) {
		this.id = marca.getId();
		this.nome = marca.getNome();
	}
	
	
	
	public MinimalMarcaDTO() {
		
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
