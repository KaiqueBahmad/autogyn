package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Cliente;
import com.pi.autogyn.persistencia.entidades.Marca;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;
import com.pi.autogyn.servicos.dto.CadastrarClienteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

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
	
	public static Cliente getByCPF(String cpf) throws SQLException {
		String sql = "select * from cliente where cpf = ?;";
		PreparedStatement statment = conn.prepareStatement(sql);
		statment.setString(1, cpf);
		ResultSet rs = statment.executeQuery();
		if (rs.next()) {
			return new Cliente(rs);
		}
		return null;
		
	}
	
	public static Cliente getByCNPJ(String cnpj) throws SQLException {
		String sql = "select * from cliente where cnpj = ?;";
		PreparedStatement statment = conn.prepareStatement(sql);
		statment.setString(1, cnpj);
		ResultSet rs = statment.executeQuery();
		if (rs.next()) {
			return new Cliente(rs);
		}
		return null;
	}
	
	
	public static Long insert(Cliente cliente) throws SQLException {
	    String sql = 
	        "INSERT INTO cliente (nome, email, logradouro, complemento, numero, " +
	        "cep, cidade, uf, ddd, telefone, ddd2, telefone2, " +
	        "cnpj, contato, inscricao_estadual, cpf) " +
	        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            
	    try (PreparedStatement statment = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        statment.setString(1, cliente.getNome());
	        statment.setString(2, cliente.getEmail());
	        
	        statment.setString(3, cliente.getEndereco().getLogradouro());
	        statment.setString(4, cliente.getEndereco().getComplemento());
	        statment.setString(5, cliente.getEndereco().getNumero());
	        statment.setString(6, cliente.getEndereco().getCep());
	        statment.setString(7, cliente.getEndereco().getCidade());
	        statment.setString(8, cliente.getEndereco().getUf());
	        
	        statment.setInt(9, cliente.getTelefone().getDdd());
	        statment.setInt(10, cliente.getTelefone().getTelefone());
	        
	        if (cliente.getTelefone2().isPresent()) {
	            statment.setInt(11, cliente.getTelefone2().get().getDdd());
	            statment.setInt(12, cliente.getTelefone2().get().getTelefone());
	        } else {
	            statment.setNull(11, Types.INTEGER);
	            statment.setNull(12, Types.INTEGER);
	        }
	        
	        if (cliente.getPessoaJuridica().isPresent()) {
	            statment.setString(13, cliente.getPessoaJuridica().get().getCnpj());
	            statment.setString(14, cliente.getPessoaJuridica().get().getContato());
	            statment.setString(15, cliente.getPessoaJuridica().get().getInscricaoEstadual());
	            statment.setNull(16, Types.VARCHAR);
	        } else if (cliente.getPessoaFisica().isPresent()) {
	            statment.setNull(13, Types.VARCHAR);
	            statment.setNull(14, Types.VARCHAR);
	            statment.setNull(15, Types.VARCHAR);
	            statment.setString(16, cliente.getPessoaFisica().get().getCpf());
	        } else {
	            statment.setNull(13, Types.VARCHAR);
	            statment.setNull(14, Types.VARCHAR);
	            statment.setNull(15, Types.VARCHAR);
	            statment.setNull(16, Types.VARCHAR);
	        }
	        
	        statment.executeUpdate();
	        
	        ResultSet rs = statment.getGeneratedKeys();
	        if (rs.next()) {
	            return rs.getLong(1);
	        }	
	        return null;
	    }
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getByCNPJ("98765432109876"));
	}
	
}
