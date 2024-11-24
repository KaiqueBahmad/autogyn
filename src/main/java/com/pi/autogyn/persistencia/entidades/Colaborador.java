package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Colaborador {
	private String cpf;
	private String nome;
	
	public Colaborador(ResultSet rs) throws SQLException {
		this.cpf = rs.getString("cpf");
		this.nome = rs.getString("nome");
	}

	@Override
	public String toString() {
		return "Colaborador [cpf=" + cpf + ", nome=" + nome + "]";
	}

}
