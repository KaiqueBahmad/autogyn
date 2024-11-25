package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Modelo;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Modelo> getAll() throws SQLException {
        String sql = "select * from modelo;";
        List<Modelo> modelos = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	modelos.add(new Modelo(rs));
        }
        return modelos;
	}
	
	public static List<Modelo> getAllModelosOfMarca(Long idMarca) throws SQLException {
		String sql = "select * from modelo where id_marca = "+idMarca+";";
        List<Modelo> modelos = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	modelos.add(new Modelo(rs));
        }
        return modelos;
	}
	
	public static Modelo getById(Long idModelo) throws SQLException {
		String sql = "select * from modelo where id_modelo = "+idModelo+";";
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	return new Modelo(rs);
        }
        return null;
	}
	
	public static boolean insert(Long idMarca, String nomeModelo) {
		String sql = "INSERT INTO modelo (id_marca, nome) VALUES (?, ?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, idMarca);
			stmt.setString(2, nomeModelo);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
	
}
