package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Colaborador;
import com.pi.autogyn.persistencia.entidades.ItemServico;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.EasyQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemServicoDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<ItemServico> getAll() throws SQLException {
        String sql = "select * from item_servico;";
        List<ItemServico> servicos = new LinkedList<>();
        ResultSet rs = EasyQuery.exec(conn, sql);
        while(rs.next()) {
        	servicos.add(new ItemServico(rs));
        }
        return servicos;
	}
	
	public static List<ItemServico> getByIdOs(Long idOs) throws SQLException {
		String sql = "SELECT * FROM item_servico WHERE id_os = "+idOs+";";
		List<ItemServico> servicos = new LinkedList<>();
		ResultSet rs = EasyQuery.exec(conn, sql);
		while(rs.next()) {
			servicos.add(new ItemServico(rs));
		}
		return servicos;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
	
}
