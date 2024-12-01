package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.pi.autogyn.persistencia.dao.ClienteDao;
import com.pi.autogyn.persistencia.dao.ItemPecaDao;
import com.pi.autogyn.persistencia.dao.ItemServicoDao;
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
	private List<ItemServico> itensServico;
	public OS() {
		
	}
	
	public OS(ResultSet source) throws SQLException {
		this.id = source.getLong("id_os");
		this.data = source.getDate("data");
		this.valor_total = source.getDouble("valor_total");
		this.valor_pago = source.getDouble("valor_pago");
		if (source.getString("etapa") != null) {
			this.etapa = Etapa.valueOf(source.getString("etapa").toUpperCase());
		} else {
			this.etapa = Etapa.CANCELADO;
		}
		this.veiculoPlaca = source.getString("placa");
		this.idCliente = source.getLong("id_cliente");	this.lazyload = true;
	}
	
	private boolean lazyload = false;
	public void setLazyload(boolean ligado) {
		this.lazyload = ligado;
	}

	public Cliente getCliente() {
		if (this.cliente == null && lazyload) {
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

	public Veiculo getVeiculo() {
		if (this.veiculo == null && lazyload) {
			try {
				this.veiculo = VeiculoDao.getByPlaca(veiculoPlaca);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.veiculo;
	}
	
	public List<ItemPeca> getItensPeca() {
		if (this.itensPeca == null && lazyload) {
			try {
				this.itensPeca = ItemPecaDao.getByIdOs(this.id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.itensPeca;
	}
	
	public List<ItemServico> getItensServico() {
		if (this.itensServico == null && lazyload) {
			try {
				this.itensServico = ItemServicoDao.getByIdOs(this.id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.itensServico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public double getValor_pago() {
		return valor_pago;
	}

	public void setValor_pago(double valor_pago) {
		this.valor_pago = valor_pago;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public String getVeiculoPlaca() {
		return veiculoPlaca;
	}

	public void setVeiculoPlaca(String veiculoPlaca) {
		this.veiculoPlaca = veiculoPlaca;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setItensPeca(List<ItemPeca> itensPeca) {
		this.itensPeca = itensPeca;
	}

	public void setItensServico(List<ItemServico> itensServico) {
		this.itensServico = itensServico;
	}
	
	
}
