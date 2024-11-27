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

	@Override
	public String toString() {
		return "Servico [id=" + id + ", descricao=" + descricao + ", valor=" + valor + "]";
	}
	
	
	
}
