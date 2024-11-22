package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Cliente;
import com.pi.autogyn.persistencia.entidades.Marca;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Cliente> getAll() throws SQLException {
        String sql = "select * from cliente;";
        List<Cliente> clientes = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	Cliente cliente = new Cliente(rs);
        	clientes.add(cliente);
        }
        return clientes;
	}
	
	public static Cliente getById(Long id) throws SQLException {
		String sql = "select * from cliente where id_cliente = "+id+";";
		ResultSet rs = QueryUtils.exec(conn, sql);
		while(rs.next()) {
			return new Cliente(rs);
		}
		return null;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
	
}
