package com.pi.autogyn.persistencia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.ItemPeca;
import com.pi.autogyn.persistencia.entidades.Peca;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

public class ItemPecaDao {
private static Connection conn = (new ConexaoBD()).getInstance();
	
	public static List<ItemPeca> getAll() throws SQLException {
        String sql = "select * from item_peca;";
        List<ItemPeca> itensPeca = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	itensPeca.add(new ItemPeca(rs));
        }
        return itensPeca;
	}
	
	public static List<ItemPeca> getByIdOs(Long idOs) throws SQLException {
		String sql = "select * from item_peca where id_os = "+idOs+";";
		ResultSet rs = QueryUtils.exec(conn, sql);
		List<ItemPeca> itensPeca = new LinkedList<>();
        while(rs.next()) {
        	itensPeca.add(new ItemPeca(rs));
        }
        return itensPeca;
	}
}
