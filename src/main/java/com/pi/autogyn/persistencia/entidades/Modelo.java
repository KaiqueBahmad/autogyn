package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pi.autogyn.persistencia.dao.MarcaDao;

public class Modelo {
	private Long id;
	private String nome;
	
	private Long idMarca;
	private Marca marca;
	
	
	public Modelo(ResultSet rs) throws SQLException {
		this.id = rs.getLong("id_modelo");
		this.nome = rs.getString("nome");
	}

	public Marca getMarca() {
		if (this.marca == null) {
			try {
				this.marca = MarcaDao.getById(idMarca);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.marca;
	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", nome=" + nome + ", idMarca=" + idMarca + ", marca=" + getMarca() + "]";
	}
	
	
	

}
