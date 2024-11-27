package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Marca;
import com.pi.autogyn.persistencia.entidades.Propriedade;
import com.pi.autogyn.persistencia.entidades.Veiculo;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VeiculoDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Veiculo> getAll() throws SQLException {
        String sql = "select * from veiculo;";
        List<Veiculo> marcas = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	Veiculo veiculo = new Veiculo(rs);
        	marcas.add(veiculo);
        }
        return marcas;
	}
	
	public static Veiculo getByPlaca(String placa) throws SQLException {
	    String sql = "SELECT * FROM veiculo WHERE veiculo.placa = ?";
	    
	    try (PreparedStatement statment = conn.prepareStatement(sql)) {
	    	statment.setString(1, placa);
	        ResultSet rs = statment.executeQuery();
	        if (rs.next()) {
	            return new Veiculo(rs);
	        }
	        return null;
	    }
	}
	
	public static void main(String[] args) throws SQLException {
		for (Veiculo veiculo: getAll()) {
			for (Propriedade prop: veiculo.getPropriedades()) {
				System.out.println(prop);
			}			
		}
	}
	
}
