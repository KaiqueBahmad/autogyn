package com.pi.autogyn.persistencia.entidades;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pi.autogyn.persistencia.dao.ClienteDao;
import com.pi.autogyn.persistencia.dao.VeiculoDao;

public class Propriedade {
	private Long id;
	private Date dataInicio;
	private Date dataFim;
	
	private String placa;
	private Veiculo veiculo;
	
	private Long idCliente;
	private Cliente cliente;
	
	public Propriedade() {
		
	}
	
	public Propriedade(ResultSet source) throws SQLException {
		this.id = source.getLong("id_propriedade");
		this.dataInicio = source.getDate("data_inicio");
		this.dataFim = source.getDate("data_fim");
		this.placa = source.getString("placa");
		this.idCliente = source.getLong("id_cliente");
	}

	public Veiculo getVeiculo() {
		if (this.veiculo == null) {
			try {
				this.veiculo = VeiculoDao.getByPlaca(placa);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return veiculo;
	}

	public Cliente getCliente() {
		if (this.cliente == null) {
			try {
				this.cliente = ClienteDao.getById(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cliente;
	}

	@Override
	public String toString() {
		return "Propriedade [id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", placa=" + placa
				+ ", veiculo=" + veiculo + ", idCliente=" + idCliente + ", cliente=" + cliente + "]";
	}
	
	

}
