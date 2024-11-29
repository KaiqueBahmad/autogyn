package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Colaborador;
import com.pi.autogyn.persistencia.entidades.Modelo;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ColaboradorDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Colaborador> getAll() throws SQLException {
        String sql = "select * from colaborador;";
        List<Colaborador> colaboradores = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	colaboradores.add(new Colaborador(rs));
        }
        return colaboradores;
	}
	
	public static Colaborador getById(String cpfColaborador) throws SQLException {
		   String sql = "SELECT * FROM colaborador WHERE cpf = ?";
		   PreparedStatement statment = conn.prepareStatement(sql);
		   statment.setString(1, cpfColaborador);
	       ResultSet rs = statment.executeQuery();
	       if (rs.next()) {
	           return new Colaborador(rs);
	       }
	       return null;
		}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
	
	public static String insert(String cpf, String nome) throws SQLException {
	    String sql = "INSERT INTO colaborador (cpf, nome) VALUES (?, ?)";
	    
        if (getById(cpf) != null) {
            return "CPF já utilizado";
        }
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        stmt.setString(2, nome);
        
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
        	return null;
        }
        return "Colaborador não criado";
	}
}
