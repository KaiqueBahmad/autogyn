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
		this.cpfColaborador = source.getString("cpf_colaborador");
	}
	
	public Servico getServico() {
		if (this.servico == null) {
			try {
				this.servico = ServicoDao.getById(this.idServico);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.servico;
	}
	
	public Colaborador getColaborador() {
		if (this.colaborador == null) {
			try {
				this.colaborador = ColaboradorDao.getById(this.cpfColaborador);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.colaborador;
	}
	
}
