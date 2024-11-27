package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.dao.MarcaDao;

public class Modelo {
	private Long id;
	private String nome;
	
	private Long idMarca;
	private Marca marca;
	
	
	public Modelo(ResultSet rs) throws SQLException {
		this.id = rs.getLong("id_modelo");
		this.nome = rs.getString("nome");
		this.idMarca = rs.getLong("id_marca");	this.lazyload = true;
	}
	
	private boolean lazyload = false;
	public void setLazyload(boolean ligado) {
		this.lazyload = ligado;
	}

	public Marca getMarca() {
		if (this.marca == null && lazyload) {
			try {
				this.marca = MarcaDao.getById(this.idMarca);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.marca;
	}
	
	

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", nome=" + nome + ", idMarca=" + idMarca + ", marca=" + getMarca().getNome() + "]";
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

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	
	

}
