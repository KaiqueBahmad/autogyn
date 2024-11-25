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
	
	public Endereco() {
		
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


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
	
}
