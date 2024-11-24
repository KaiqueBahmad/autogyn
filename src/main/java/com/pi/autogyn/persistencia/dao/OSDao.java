package com.pi.autogyn.persistencia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.OS;
import com.pi.autogyn.persistencia.entidades.Propriedade;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.EasyQuery;

public class OSDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<OS> getAll() throws SQLException {
        String sql = "select * from ordem_servico;";
        List<OS> oss = new LinkedList<>();
        ResultSet rs = EasyQuery.exec(conn, sql);
        while(rs.next()) {
        	oss.add(new OS(rs));
        }
        return oss;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
}
