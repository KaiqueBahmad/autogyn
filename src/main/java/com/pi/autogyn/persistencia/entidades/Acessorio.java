package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Acessorio {

	private Long id;
	private String descricao;
	
	public Acessorio() {
		
	}
	public Acessorio(ResultSet source) throws SQLException {
		this.id = source.getLong("id_acessorio");
		this.descricao = source.getString("descricao");
		this.lazyload = true;
	}
	
	private boolean lazyload = false;
	public void setLazyload(boolean ligado) {
		this.lazyload = ligado;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Acessorio [id=" + id + ", descricao=" + descricao + "]";
	}
	
	
}
