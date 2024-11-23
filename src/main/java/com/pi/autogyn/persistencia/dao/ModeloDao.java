package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Modelo;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.EasyQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Modelo> getAll() throws SQLException {
        String sql = "select * from modelo;";
        List<Modelo> modelos = new LinkedList<>();
        ResultSet rs = EasyQuery.exec(conn, sql);
        while(rs.next()) {
        	modelos.add(new Modelo(rs));
        }
        return modelos;
	}
	
	public static List<Modelo> getAllModeloOfMarca(Long idMarca) throws SQLException {
		String sql = "select * from modelo where marca = "+idMarca+";";
        List<Modelo> modelos = new LinkedList<>();
        ResultSet rs = EasyQuery.exec(conn, sql);
        while(rs.next()) {
        	modelos.add(new Modelo(rs));
        }
        return modelos;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
	
}
