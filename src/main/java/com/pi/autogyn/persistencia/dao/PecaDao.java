package com.pi.autogyn.persistencia.dao;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Peca;
import com.pi.autogyn.persistencia.entidades.Servico;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;
import com.pi.autogyn.servicos.dto.CadastrarPecaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PecaDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Peca> getAll() throws SQLException {
        String sql = "select * from pecas;";
        List<Peca> pecas = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	pecas.add(new Peca(rs));
        }
        return pecas;
	}
	
	public static boolean insert(CadastrarPecaDTO novaPeca) throws SQLException {
	    String[] campos = {"codigo", "descricao", "sku", "quantidade_estoque", "valor_unitario"};
	    String placeholders = String.join(", ", Collections.nCopies(campos.length, "?"));
	    String sql = "INSERT INTO pecas (" + String.join(", ", campos) + ") VALUES (" + placeholders + ")";
	    
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, novaPeca.getCodigo()); 
			stmt.setString(2, novaPeca.getDescricao());
			stmt.setString(3, novaPeca.getSku());
			stmt.setInt(4, novaPeca.getQuantidadeEstoque()); 
			stmt.setDouble(5, novaPeca.getValorUnitario());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Peca getById(Long idPeca) throws SQLException {
		String sql = "select * from pecas where id_peca = "+idPeca+";";
		ResultSet rs = QueryUtils.exec(conn, sql);
		if (rs.next()) {
			return new Peca(rs);
		}
		return null;
	}
	
	public static void main(String[] args) throws SQLException {
//		System.out.println(getAll());
		System.out.println(getById(1L));
	}
	
}
