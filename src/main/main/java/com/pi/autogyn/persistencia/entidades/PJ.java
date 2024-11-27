package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

public class PJ {
	private String inscricaoEstadual;
	private String contato;
	private String cnpj;
	
	PJ() {
		
	}
	
	public static Optional<PJ> create(ResultSet rs) throws SQLException {
		if (!QueryUtils.checkAttribute(rs, "cnpj")) {
			return Optional.empty();
		}
		PJ pj = new PJ();
		pj.cnpj = rs.getString("cnpj");
		pj.contato = rs.getString("contato");
		pj.inscricaoEstadual = rs.getString("inscricao_estadual");
		return Optional.of(pj);
	}

	@Override
	public String toString() {
		return "PJ [inscricaoEstadual=" + inscricaoEstadual + ", contato=" + contato + ", cnpj=" + cnpj + "]";
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
	
}
