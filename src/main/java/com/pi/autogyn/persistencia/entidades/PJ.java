package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.pi.autogyn.persistencia.ferramentas.EasyQuery;

public class PJ {
	private String inscricaoEstadual;
	private String contato;
	private String cnpj;
	
	PJ() {
		
	}
	
	public static Optional<PJ> create(ResultSet rs) throws SQLException {
		if (!EasyQuery.checkAttribute(rs, "cnpj")) {
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
	
	
	
}
