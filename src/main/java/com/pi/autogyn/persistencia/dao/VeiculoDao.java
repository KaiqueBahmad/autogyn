package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Marca;
import com.pi.autogyn.persistencia.entidades.Veiculo;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.EasyQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VeiculoDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Veiculo> getAll() throws SQLException {
        String sql = "select * from veiculo;";
        List<Veiculo> marcas = new LinkedList<>();
        ResultSet rs = EasyQuery.exec(conn, sql);
        while(rs.next()) {
        	Veiculo veiculo = new Veiculo(rs);
        	marcas.add(veiculo);
        }
        return marcas;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
	
}
