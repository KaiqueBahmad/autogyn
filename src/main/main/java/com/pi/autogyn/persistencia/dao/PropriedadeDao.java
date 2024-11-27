package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Modelo;
import com.pi.autogyn.persistencia.entidades.Propriedade;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PropriedadeDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Propriedade> getAll() throws SQLException {
        String sql = "select * from propriedade;";
        List<Propriedade> propriedades = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	propriedades.add(new Propriedade(rs));
        }
        return propriedades;
	}
	
	public static List<Propriedade> getAllByCliente(Long idCliente) throws SQLException {
		String sql = "select * from propriedade where id_cliente = "+idCliente+";";
		List<Propriedade> propriedades = new LinkedList<>();
		ResultSet rs = QueryUtils.exec(conn, sql);
		while (rs.next()) {
			propriedades.add(new Propriedade(rs));
		}
		return propriedades;
	}
	
	public static List<Propriedade> getAllByPlaca(String placa) throws SQLException {
	    String sql = "SELECT * FROM propriedade WHERE placa = ?";
    	PreparedStatement stmt = conn.prepareStatement(sql);
    	stmt.setString(1, placa);
        ResultSet rs = stmt.executeQuery();
        List<Propriedade> propriedades = new LinkedList<>();
        while (rs.next()) {
            propriedades.add(new Propriedade(rs));
        }
        return propriedades;
	}
	
	
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
	
}
