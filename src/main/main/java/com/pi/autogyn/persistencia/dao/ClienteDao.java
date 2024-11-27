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
		System.out.println(getAll());
		CadastrarClienteDTO dtoPF = new CadastrarClienteDTO();
	    dtoPF.setNome("João Silva");
	    dtoPF.setEmail("joao.silva@email.com");
	    dtoPF.setPJ(false);
	    dtoPF.setCpf("12345678900");
	    dtoPF.setDdd(11);
	    dtoPF.setTelefone(999887766);
	    dtoPF.setDdd2(11);
	    dtoPF.setTelefone2(988776655);
	    dtoPF.setCep("01234567");
	    dtoPF.setCidade("São Paulo");
	    dtoPF.setUf("SP");
	    dtoPF.setNumero("123");
	    dtoPF.setLogradouro("Rua das Flores");
	    dtoPF.setComplemento("Apto 45");

	    Cliente clientePF = new Cliente(dtoPF);
	    Long idPF = insert(clientePF);
	    System.out.println("Inserted PF client with ID: " + idPF);
	    
	    // Test inserting Pessoa Jurídica
	    CadastrarClienteDTO dtoPJ = new CadastrarClienteDTO();
	    dtoPJ.setNome("Tech Solutions LTDA");
	    dtoPJ.setEmail("contato@techsolutions.com");
	    dtoPJ.setPJ(true);
	    dtoPJ.setCnpj("12345678000190");
	    dtoPJ.setInscricao_estadual("123456789");
	    dtoPJ.setNomeContato("Maria Souza");
	    dtoPJ.setDdd(11);
	    dtoPJ.setTelefone(987654321);
	    dtoPJ.setCep("04567890");
	    dtoPJ.setCidade("São Paulo");
	    dtoPJ.setUf("SP");
	    dtoPJ.setNumero("1000");
	    dtoPJ.setLogradouro("Av. Paulista");
	    dtoPJ.setComplemento("Sala 1502");

	    Cliente clientePJ = new Cliente(dtoPJ);
	    Long idPJ = insert(clientePJ);
	    System.out.println("Inserted PJ client with ID: " + idPJ);

	    // Test retrieving and displaying all clients
	    System.out.println("\nAll clients in database:");
	    List<Cliente> allClients = getAll();
	    allClients.forEach(cliente -> System.out.println(
	        "ID: " + cliente.getId() + 
	        ", Nome: " + cliente.getNome() + 
	        ", Tipo: " + (cliente.getPessoaFisica().isPresent() ? "PF" : "PJ")
	    ));

	    // Test retrieving specific client
	    System.out.println("\nRetrieving newly inserted clients:");
	    Cliente retrievedPF = getById(idPF);
	    Cliente retrievedPJ = getById(idPJ);
	    
	    System.out.println("Retrieved PF: " + retrievedPF);
	    System.out.println("Retrieved PJ: " + retrievedPJ);
		
	}
	
}
