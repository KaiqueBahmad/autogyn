package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Marca;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MarcaDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Marca> getAll() throws SQLException {
        String sql = "select * from marca;";
        List<Marca> marcas = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	Marca marca = new Marca(rs);
        	marcas.add(marca);
        }
        return marcas;
	}
	
	public static Marca getById(Long id) throws SQLException {
		String sql = "select * from marca where id_marca = "+id+";";
		ResultSet rs = QueryUtils.exec(conn, sql);
		if (rs.next()) {
			return new Marca(rs);
		}
		return null;
	}
	
	public static boolean insert(String marca) throws SQLException {
	       String sql = "INSERT INTO marca (nome) VALUES (?)";
	       
	       try {
	           PreparedStatement stmt = conn.prepareStatement(sql);
	           stmt.setString(1, marca);
	           
	           int rowsAffected = stmt.executeUpdate();
	           return rowsAffected > 0;
	           
	       } catch (SQLException e) {
	           e.printStackTrace();
	           return false;
	       }
	   }

	   public static boolean existsByNome(String nome) throws SQLException {
	       String sql = "SELECT COUNT(*) as count FROM marca WHERE nome = ?";
	       
	       PreparedStatement stmt = conn.prepareStatement(sql);
	       stmt.setString(1, nome);
	       ResultSet rs = stmt.executeQuery();
	       
	       if (rs.next()) {
	           return rs.getInt("count") > 0;
	       }
	       return false;
	   }
	
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
	
}
