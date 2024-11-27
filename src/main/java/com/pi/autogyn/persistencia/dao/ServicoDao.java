package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Servico;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServicoDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Servico> getAll() throws SQLException {
        String sql = "select * from servico;";
        List<Servico> servicos = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
            servicos.add(new Servico(rs));
        }
        return servicos;
	}
	
	public static Servico getById(Long idServico) throws SQLException {
		String sql = "select * from servico where id_servico = "+idServico+";";
		ResultSet rs = QueryUtils.exec(conn, sql);
		if (rs.next()) {
			return new Servico(rs);
		}
		return null;
	}
	
	public static boolean insert(String descricao, Double valor) throws SQLException {
		String sql = "INSERT INTO servico (descricao, valor) VALUES (?, ?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, descricao);
			stmt.setDouble(2, valor);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
		System.out.println(insert("Troca de óleo", 52.2));
	}
	
}
