package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Cliente {
	public Cliente(ResultSet rs) throws SQLException {
		this.id = rs.getLong("id_cliente");
		this.nome = rs.getString("nome");
		this.email = rs.getString("email");
		this.endereco = new Endereco(rs);
		this.telefone = new Telefone(rs);
		this.telefone2 = Telefone.createSecondary(rs);
		this.pessoaJuridica = PJ.create(rs);
		this.pessoaFisica = PF.create(rs);
	}
	
	private Long id;
	private String nome;
	private String email;
	private Endereco endereco;
	private Telefone telefone;
	private Optional<Telefone> telefone2;
	private Optional<PJ> pessoaJuridica;
	private Optional<PF> pessoaFisica;
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco + ", telefone="
				+ telefone + ", telefone2=" + telefone2 + ", pessoaJuridica=" + pessoaJuridica + ", pessoaFisica="
				+ pessoaFisica + "]";
	}
	
	
}