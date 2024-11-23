package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Endereco {
	public Endereco(ResultSet rs) throws SQLException {
		this.logradouro = rs.getString("logradouro");
		this.complemento = rs.getString("complemento");
		this.numero = rs.getString("numero");
		this.cep = rs.getString("cep");
		this.cidade = rs.getString("cidade");
		this.uf = rs.getString("uf");
	}
	private String logradouro;
	private String complemento;
	private String numero;
	private String cep;
	private String cidade;
	private String uf;
	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", complemento=" + complemento + ", numero=" + numero + ", cep="
				+ cep + ", cidade=" + cidade + ", uf=" + uf + "]";
	}
	
	
	
}
