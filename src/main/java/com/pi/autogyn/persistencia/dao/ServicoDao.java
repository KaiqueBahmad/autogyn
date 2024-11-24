package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Servico;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.EasyQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServicoDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Servico> getAll() throws SQLException {
        String sql = "select * from servico;";
        List<Servico> servicos = new LinkedList<>();
        ResultSet rs = EasyQuery.exec(conn, sql);
        while(rs.next()) {
            servicos.add(new Servico(rs));
        }
        return servicos;
	}
	
	public static Servico getById(Long idServico) throws SQLException {
		String sql = "select * from servico where id_servico = "+idServico+";";
		ResultSet rs = EasyQuery.exec(conn, sql);
		if (rs.next()) {
			return new Servico(rs);
		}
		return null;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
	
}
