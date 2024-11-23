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
	}
	@Override
	public String toString() {
		return "Acessorio [id=" + id + ", descricao=" + descricao + "]";
	}
	
	
}
