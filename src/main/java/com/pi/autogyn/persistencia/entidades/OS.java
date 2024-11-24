package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.pi.autogyn.persistencia.dao.ClienteDao;
import com.pi.autogyn.persistencia.dao.ItemPecaDao;
import com.pi.autogyn.persistencia.dao.VeiculoDao;

public class OS {
	private Long id;
	private Date data;
	private double valor_total;
	private double valor_pago;
	private Etapa etapa;
	private String veiculoPlaca;
	private Long idCliente;
	private Veiculo veiculo;
	private Cliente cliente;
	private List<ItemPeca> itensPeca;
	
	public OS() {
		
	}
	
	public OS(ResultSet source) throws SQLException {
		this.id = source.getLong("id_os");
		this.data = source.getDate("data");
		this.valor_pago = source.getDouble("valor_total");
		this.valor_pago = source.getDouble("valor_pago");
		this.etapa = Etapa.valueOf(source.getString("etapa").toUpperCase());
		this.veiculoPlaca = source.getString("placa");
		this.idCliente = source.getLong("id_cliente");
	}

	public Cliente getCliente() {
		if (this.cliente == null) {
			try {
				this.cliente = ClienteDao.getById(this.idCliente);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.cliente;
	}

	@Override
	public String toString() {
		return "OS [id=" + id + ", data=" + data + ", valor_total=" + valor_total + ", valor_pago=" + valor_pago
				+ ", etapa=" + etapa + ", veiculoPlaca=" + veiculoPlaca + ", idCliente=" + idCliente + ", veiculo="
				+ getVeiculo()+ ", cliente=" + getCliente() + "]";
	}

	private Veiculo getVeiculo() {
		if (this.veiculo == null) {
			try {
				this.veiculo = VeiculoDao.getByPlaca(veiculoPlaca);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.veiculo;
	}
	
	private List<ItemPeca> getItensPeca() {
		if (this.itensPeca == null) {
			try {
				this.itensPeca = ItemPecaDao.getByIdOs(this.id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.itensPeca;
	}
	
	
}
