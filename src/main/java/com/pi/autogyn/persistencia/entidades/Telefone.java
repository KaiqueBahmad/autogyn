package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

public class Telefone {

	private int ddd;
	private int telefone;

	public Telefone(ResultSet rs) throws SQLException {
		this.ddd = rs.getInt("ddd");
		this.telefone = rs.getInt("telefone");
	}

	public Telefone() {
	}

	public static Optional<Telefone> createSecondary(ResultSet rs) throws SQLException {
	    if (QueryUtils.checkAttribute(rs, "telefone2") && QueryUtils.checkAttribute(rs, "ddd2")) {
	        Telefone telefone = new Telefone();
	        telefone.telefone = rs.getInt("telefone2");
	        telefone.ddd = rs.getInt("ddd2");
	        return Optional.of(telefone);
	    }
	    return Optional.empty();
	}
}
