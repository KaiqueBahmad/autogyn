package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Servico {
	
	private Long id;
	private String descricao;
	private Double valor;
	
	public Servico() {
		
	}
	
	public Servico(ResultSet source) throws SQLException {
		this.id = source.getLong("id_servico");
		this.descricao = source.getString("descricao");
		this.valor = source.getDouble("valor");	this.lazyload = true;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isLazyload() {
		return lazyload;
	}

	@Override
	public String toString() {
		return "Servico [id=" + id + ", descricao=" + descricao + ", valor=" + valor + "]";
	}
	
	
	
}
