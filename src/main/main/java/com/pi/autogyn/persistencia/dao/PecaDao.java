package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Peca;
import com.pi.autogyn.persistencia.entidades.Servico;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PecaDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Peca> getAll() throws SQLException {
        String sql = "select * from pecas;";
        List<Peca> pecas = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	pecas.add(new Peca(rs));
        }
        return pecas;
	}
	
	public static Peca getById(Long idPeca) throws SQLException {
		String sql = "select * from pecas where id_peca = "+idPeca+";";
		ResultSet rs = QueryUtils.exec(conn, sql);
		if (rs.next()) {
			return new Peca(rs);
		}
		return null;
	}
	
	public static void main(String[] args) throws SQLException {
//		System.out.println(getAll());
		System.out.println(getById(1L));
	}
	
}
