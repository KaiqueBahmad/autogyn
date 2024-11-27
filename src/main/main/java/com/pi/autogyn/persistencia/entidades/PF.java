package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

public class PF {
	private String cpf;

	public PF() {
		
	}
	
	public static Optional<PF> create(ResultSet rs) throws SQLException {
		if (!QueryUtils.checkAttribute(rs, "cpf")) {
			return Optional.empty();
		}
		PF pf = new PF();
		pf.cpf = rs.getString("cpf");
		return Optional.of(pf);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
}
