package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.pi.autogyn.persistencia.dao.ColaboradorDao;
import com.pi.autogyn.persistencia.dao.ServicoDao;

public class ItemServico {
	private Long id;
	private double valorTotal;
	private Date dataInicio;
	private Date dataFim;
	private int quantidade;
	private double valorUnitario;
	
	private long idServico;
	private Servico servico;
	
	private String cpfColaborador;
	private Colaborador colaborador;
	
	public ItemServico() {
		
	}
	
	public ItemServico(ResultSet source) throws SQLException {
		this.id = source.getLong("id_item_servico");
		this.valorTotal = source.getDouble("valor_total");
		this.dataInicio = source.getDate("data_inicio");
		this.dataFim = source.getDate("data_fim");
		this.quantidade = source.getInt("quantidade");
		this.valorUnitario = source.getDouble("valor_unitario");
		this.idServico = source.getLong("id_servico");
		this.cpfColaborador = source.getString("cpf_colaborador");	this.lazyload = true;
	}
	
	private boolean lazyload = false;
	public void setLazyload(boolean ligado) {
		this.lazyload = ligado;
	}
	
	
	
	
	public Servico getServico() {
		if (this.servico == null && lazyload) {
			try {
				this.servico = ServicoDao.getById(this.idServico);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.servico;
	}
	
	public Colaborador getColaborador() {
		if (this.colaborador == null && lazyload) {
			try {
				this.colaborador = ColaboradorDao.getById(this.cpfColaborador);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.colaborador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public long getIdServico() {
		return idServico;
	}

	public void setIdServico(long idServico) {
		this.idServico = idServico;
	}

	public String getCpfColaborador() {
		return cpfColaborador;
	}

	public void setCpfColaborador(String cpfColaborador) {
		this.cpfColaborador = cpfColaborador;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	
}
