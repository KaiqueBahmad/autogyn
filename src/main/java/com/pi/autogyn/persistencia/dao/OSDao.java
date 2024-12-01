package com.pi.autogyn.persistencia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.OS;
import com.pi.autogyn.persistencia.entidades.Propriedade;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;
import com.pi.autogyn.servicos.dto.CadastrarOSDTO;

public class OSDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<OS> getAll() throws SQLException {
        String sql = "select * from ordem_servico;";
        List<OS> oss = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	oss.add(new OS(rs));
        }
        return oss;
	}
	
	public static OS getById(Long idOs) throws SQLException {
		String sql = "select * from ordem_servico where id_os = "+idOs+";";
		ResultSet rs = QueryUtils.exec(conn, sql);
		if (rs.next()) {
			return new OS(rs);
		}
		return null;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}

	public static void criarOS(CadastrarOSDTO novaOS) {
		
	}
}
