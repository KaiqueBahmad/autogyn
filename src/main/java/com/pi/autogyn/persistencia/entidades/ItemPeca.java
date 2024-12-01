package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pi.autogyn.persistencia.dao.OSDao;
import com.pi.autogyn.persistencia.dao.PecaDao;

public class ItemPeca {
	private Long id;
	private int quantidade;
	private double valor_total;
	private double valor_unitario;
	private long idOs;
	private OS os;
	private long idPeca;
	private Peca peca;
	
	public ItemPeca() {
		
	}
	
	public ItemPeca(ResultSet source) throws SQLException {
		this.id = source.getLong("id_item_peca");
		this.quantidade = source.getInt("quantidade");
		this.valor_total = source.getDouble("valor_total");
		this.valor_unitario = source.getDouble("valor_unitario");
		this.idOs = source.getLong("id_os");
		this.idPeca = source.getLong("id_peca");	this.lazyload = true;
	}
	
	private boolean lazyload = false;
	public void setLazyload(boolean ligado) {
		this.lazyload = ligado;
	}
	
	public OS getOs() {
		if (this.os == null && lazyload) {
			try {
				this.os = OSDao.getById(this.idOs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.os;
	}
	
	public Peca getPeca() {
		if (this.peca == null && lazyload) {
			try {
				this.peca = PecaDao.getById(this.idPeca);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.peca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public long getIdOs() {
		return idOs;
	}

	public void setIdOs(long idOs) {
		this.idOs = idOs;
	}

	public long getIdPeca() {
		return idPeca;
	}

	public void setIdPeca(long idPeca) {
		this.idPeca = idPeca;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}
	
}
