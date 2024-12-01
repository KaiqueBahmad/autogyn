package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Colaborador;
import com.pi.autogyn.persistencia.entidades.ItemServico;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemServicoDao {
	private static Connection conn = (new ConexaoBD()).getInstance();
	
	public static List<ItemServico> getAll() throws SQLException {
        String sql = "select * from item_servico;";
        List<ItemServico> servicos = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	servicos.add(new ItemServico(rs));
        }
        return servicos;
	}
	
	public static List<ItemServico> getByIdOs(Long idOs) throws SQLException {
		String sql = "SELECT * FROM item_servico WHERE id_os = "+idOs+";";
		List<ItemServico> servicos = new LinkedList<>();
		ResultSet rs = QueryUtils.exec(conn, sql);
		while(rs.next()) {
			servicos.add(new ItemServico(rs));
		}
		return servicos;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}

	public static String terminarServico(Long idServico) throws SQLException {
	    String sql = "UPDATE item_servico SET data_fim = CURRENT_DATE WHERE id_servico = ?";
	    
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setLong(1, idServico);
	    
	    int rowsAffected = stmt.executeUpdate();
	    
	    if (rowsAffected > 0) {
	        return null;
	    } else {
	        return "Serviço não encontrado ou já finalizado";
	    }
	}

	public static String retomarServico(Long idServico) throws SQLException {
	    String sql = "UPDATE item_servico SET data_fim = NULL WHERE id_servico = ?";
	    
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setLong(1, idServico);
	    
	    int rowsAffected = stmt.executeUpdate();
	    
	    if (rowsAffected > 0) {
	        return null;
	    } else {
	        return "Serviço não encontrado ou já em andamento";
	    }
	}
	
}
