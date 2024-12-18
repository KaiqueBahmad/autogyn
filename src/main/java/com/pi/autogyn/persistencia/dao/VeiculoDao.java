package com.pi.autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.entidades.Marca;
import com.pi.autogyn.persistencia.entidades.Propriedade;
import com.pi.autogyn.persistencia.entidades.Veiculo;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;
import com.pi.autogyn.servicos.dto.CadastrarVeiculoDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class VeiculoDao {
	private static Connection conn = (new ConexaoBD()).getInstance();
	
	public static List<Veiculo> getAll() throws SQLException {
        String sql = "select * from veiculo;";
        List<Veiculo> marcas = new LinkedList<>();
        ResultSet rs = QueryUtils.exec(conn, sql);
        while(rs.next()) {
        	Veiculo veiculo = new Veiculo(rs);
        	marcas.add(veiculo);
        }
        return marcas;
	}
	
	public static Veiculo getByPlaca(String placa) throws SQLException {
	    String sql = "SELECT * FROM veiculo WHERE veiculo.placa = ?";
	    
	    try (PreparedStatement statment = conn.prepareStatement(sql)) {
	    	statment.setString(1, placa);
	        ResultSet rs = statment.executeQuery();
	        if (rs.next()) {
	            return new Veiculo(rs);
	        }
	        return null;
	    }
	}
	public static String insert(CadastrarVeiculoDTO novoVeiculo) throws IllegalStateException {
	    String sql = "INSERT INTO veiculo (placa, ano_fabricacao, num_chassi, km, num_patrimonio, ano_modelo, id_modelo) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING placa";
	
	    try (PreparedStatement statement = conn.prepareStatement(sql)) {
	        statement.setString(1, novoVeiculo.getPlaca());
	        statement.setInt(2, novoVeiculo.getAno_fabricacao());
	        statement.setString(3, novoVeiculo.getNumero_chassi());
	        statement.setInt(4, novoVeiculo.getQuilometragem());
	        statement.setInt(5, novoVeiculo.getNumero_patrimonio());
	        statement.setInt(6, novoVeiculo.getAno_modelo());
	        statement.setLong(7, novoVeiculo.getId_modelo());
	        
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            return rs.getString(1);
	        }
	        return null;
	        
	    } catch (SQLIntegrityConstraintViolationException e) {
	        if (e.getMessage().contains("veiculo_pkey")) {
	            throw new IllegalStateException("Já existe um veículo cadastrado com esta placa.");
	        } else if (e.getMessage().contains("veiculo_num_chassi_key")) {
	            throw new IllegalStateException("Já existe um veículo cadastrado com este número de chassi.");
	        } else if (e.getMessage().contains("veiculo_num_patrimonio_key")) {
	            throw new IllegalStateException("Já existe um veículo cadastrado com este número de patrimônio.");
	        } else if (e.getMessage().contains("veiculo_id_modelo_fkey")) {
	            throw new IllegalStateException("O modelo informado não existe no sistema.");
	        }
	        throw new IllegalStateException("Erro de violação de restrição no banco de dados: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        throw new IllegalStateException("O número do chassi deve conter apenas números.");
	    } catch (SQLException e) {
	        throw new IllegalStateException("Erro ao inserir veículo no banco de dados: " + e.getMessage());
	    }
	}
	
	
	public static void main(String[] args) throws SQLException {
		for (Veiculo veiculo: getAll()) {
			for (Propriedade prop: veiculo.getPropriedades()) {
				System.out.println(prop);
			}			
		}
	}

	public static void trocarProprietario(String placa, Long idNovoProprietario) throws SQLException {
	    String sqlUpdateAntigo = "UPDATE propriedade SET data_fim = ? WHERE placa = ? AND data_fim IS NULL";
	    String sqlNovoProprietario = "INSERT INTO propriedade (data_inicio, data_fim, placa, id_cliente) VALUES (?, ?, ?, ?)";
	    
	    Date dataAtual = new Date(System.currentTimeMillis());
	    conn.setAutoCommit(false);
	    try {
	        PreparedStatement stmtUpdateAntigo = conn.prepareStatement(sqlUpdateAntigo);
	        stmtUpdateAntigo.setDate(1, dataAtual);
	        stmtUpdateAntigo.setString(2, placa);
	        stmtUpdateAntigo.executeUpdate();
	        
	        PreparedStatement stmtNovoProprietario = conn.prepareStatement(sqlNovoProprietario);
	        stmtNovoProprietario.setDate(1, dataAtual);
	        stmtNovoProprietario.setNull(2, java.sql.Types.DATE); 
	        stmtNovoProprietario.setString(3, placa);
	        stmtNovoProprietario.setLong(4, idNovoProprietario);
	        stmtNovoProprietario.executeUpdate();
	        conn.commit();
	    } catch (SQLException e) {
	        conn.rollback();
	        throw e;
	    } finally {
	        conn.setAutoCommit(true);
	    }
	}
	
	
	public static void atualizarQuilometragem(String placa, Integer quilometragem) throws SQLException {
	    String sqlUpdate = "UPDATE veiculo SET quilometragem = ? WHERE placa = ?";
	    
	    Veiculo veiculo = getByPlaca(placa);
        int quilometragemAtual = veiculo.getKm();

		//eu ia colocar mas talvez a pessoa insira uma quilometragem errada
		//if (quilometragem < quilometragemAtual) {
		//    throw new IllegalArgumentException(
		//        "Nova quilometragem (" + quilometragem + ") não pode ser menor que a atual (" + quilometragemAtual + ")"
		//    );
		//}
        
        PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
        stmtUpdate.setInt(1, quilometragem);
        stmtUpdate.setString(2, placa);
        int rowsAffected = stmtUpdate.executeUpdate();
        
        if (rowsAffected == 0) {
            throw new SQLException("Falha ao atualizar quilometragem. Veículo não encontrado.");
        }
	}

	public static void addAcessorio(String placa, Long acessorio) throws IllegalStateException, SQLException {
	    String sql = "INSERT INTO acessorio_veiculo (placa, id_acessorio) VALUES (?, ?)";
	    
	    try (PreparedStatement statement = conn.prepareStatement(sql)) {
	        statement.setString(1, placa);
	        statement.setLong(2, acessorio);
	        statement.executeUpdate();
	        
	    } catch (SQLIntegrityConstraintViolationException e) {
	        if (e.getMessage().contains("acessorio_veiculo_pkey")) {
	            throw new IllegalStateException("Este acessório já está cadastrado para este veículo.");
	        } else if (e.getMessage().contains("acessorio_veiculo_placa_fkey")) {
	            throw new IllegalStateException("Veículo não encontrado.");
	        } else if (e.getMessage().contains("acessorio_veiculo_id_acessorio_fkey")) {
	            throw new IllegalStateException("Acessório não encontrado no sistema.");
	        }
	        throw new IllegalStateException("Erro de violação de restrição no banco de dados: " + e.getMessage());
	    } catch (SQLException e) {
	        throw new IllegalStateException("Erro ao adicionar acessório ao veículo: " + e.getMessage());
	    }
	}

	
}
