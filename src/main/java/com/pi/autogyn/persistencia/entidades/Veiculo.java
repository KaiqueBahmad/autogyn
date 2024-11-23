package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pi.autogyn.persistencia.dao.AcessorioDao;
import com.pi.autogyn.persistencia.dao.ModeloDao;

public class Veiculo {
	private String placa;
	private int km;
	private int anoFabricacao;
	private int numPatrimonio;
	private int numChassi;
	private int anoModelo;
	private Modelo modelo;
	private Long modelo_id;
	private List<Acessorio> acessorios;
	
	public Veiculo(ResultSet rs) throws SQLException {
		this.placa = rs.getString("placa");
		this.anoFabricacao = rs.getInt("ano_fabricacao");
		this.numChassi = rs.getInt("num_chassi");
		this.km = rs.getInt("km");
		this.numPatrimonio = rs.getInt("num_patrimonio");
		this.anoModelo = rs.getInt("ano_modelo");
		this.modelo = null;
		this.modelo_id = rs.getLong("id_modelo");
	}
	
	public Modelo getModelo() {
		if (modelo == null) {
			try {
				this.modelo = ModeloDao.getById(this.modelo_id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.modelo;
	}
	
	public List<Acessorio> getAcessorios() {
		if (acessorios == null) {
			try {
				this.acessorios = AcessorioDao.getAllAcessoriosOfVeiculo(this.placa);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.acessorios;
	}

	@Override
	public String toString() {
		return "Veiculo [placa=" + placa + ", km=" + km + ", anoFabricacao=" + anoFabricacao + ", numPatrimonio="
				+ numPatrimonio + ", numChassi=" + numChassi + ", anoModelo=" + anoModelo + ", modelo=" + getModelo()
				+ ", modelo_id=" + modelo_id + ", acessorios=" + getAcessorios() + "]";
	}
	
	

	
}
