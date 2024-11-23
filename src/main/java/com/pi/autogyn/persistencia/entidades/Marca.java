package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.dao.ModeloDao;

public class Marca {
	private Long id;
	private String nome;
	private List<Modelo> modelos;
	
	
	public Marca(ResultSet rs) throws SQLException {
		this.id = rs.getLong("id_marca");
		this.nome = rs.getString("nome");
	}

	public Marca() {
		
	}
	
	

	@Override
	public String toString() {
		return "Marca [id=" + id + ", nome=" + nome + ", modelos=" + getModelos() + "]";
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

	public List<Modelo> getModelos() {
		if (this.modelos == null) {
			try {
				this.modelos = ModeloDao.getAllModeloOfMarca(this.id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}
	
	
	
	
}
