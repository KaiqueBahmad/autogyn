package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Peca {
	private Long id;
	private String codigo;
	private String descricao;
	private String sku;
	private int quantidadeEstoque;
	private double valorUnitario;
	
	public Peca() {
		
	}
	
	public Peca(ResultSet source) throws SQLException {
		this.id = source.getLong("id_peca");
		this.codigo = source.getString("codigo");
		this.descricao = source.getString("descricao");
		this.sku = source.getString("sku");
		this.quantidadeEstoque = source.getInt("quantidade_estoque");
		this.valorUnitario = source.getDouble("valor_unitario");	this.lazyload = true;
	}
	
	private boolean lazyload = false;
	public void setLazyload(boolean ligado) {
		this.lazyload = ligado;
	}

	@Override
	public String toString() {
		return "Peca [id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", sku=" + sku
				+ ", quantidadeEstoque=" + quantidadeEstoque + ", valorUnitario=" + valorUnitario + "]";
	}
	
	
	
	
}
