package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.ferramentas.RelationProvider;


public class Marca {
	private Long id;
	private String nome;
	private List<Modelo> modelos;
	private EnumMap<Relacionamentos, RelationProvider> providers = new EnumMap<>(Relacionamentos.class);
	
	public void setProviderFor(Relacionamentos relacao, RelationProvider provider) {
		this.providers.put(relacao, provider);
	}
	
	public enum Relacionamentos { 
		Modelos;
	}
	
	
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
				ResultSet rs = this.providers.get(Relacionamentos.Modelos).provide();
				this.modelos = new LinkedList<>();
				while (rs.next()) {
					modelos.add(new Modelo(rs));
				}
			} catch (SQLException e) {
			}
		}
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}
	
	
	
	
}
