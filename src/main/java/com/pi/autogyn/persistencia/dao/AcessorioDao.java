package com.pi.autogyn.persistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Acessorio;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

public class AcessorioDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Acessorio> getAll() throws SQLException {
        String sql = "select * from acessorio;";
        List<Acessorio> acessorios = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
            acessorios.add(new Acessorio(rs));
        }
        return acessorios;
	}
	
	public static List<Acessorio> getAllAcessoriosOfVeiculo(String placa) throws SQLException {
	   String sql =
	       "SELECT "
	       + "    a.* "
	       + "FROM "
	       + "    Acessorio a "
	       + "    INNER JOIN acessorio_veiculo av ON a.id_acessorio = av.id_acessorio "
	       + "WHERE "
	       + "    av.placa = ?";
	       
	   List<Acessorio> acessorios = new LinkedList<>();
	   PreparedStatement statment = conn.prepareStatement(sql);
	   statment.setString(1, placa);
       ResultSet rs = statment.executeQuery();
       
       while (rs.next()) {
           acessorios.add(new Acessorio(rs));
       }
       
       rs.close();
       return acessorios;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
}
