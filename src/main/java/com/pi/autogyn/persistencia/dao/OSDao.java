package com.pi.autogyn.persistencia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;

import com.pi.autogyn.persistencia.entidades.ItemServico;
import com.pi.autogyn.persistencia.entidades.OS;
import com.pi.autogyn.persistencia.entidades.Propriedade;
import com.pi.autogyn.persistencia.entidades.Servico;
import com.pi.autogyn.persistencia.entidades.Veiculo;
import com.pi.autogyn.persistencia.ferramentas.ConexaoBD;
import com.pi.autogyn.persistencia.ferramentas.QueryUtils;
import com.pi.autogyn.servicos.dto.CadastrarOSDTO;
import com.pi.autogyn.servicos.dto.ColaboradorServicoDTO;
import com.pi.autogyn.servicos.dto.PecaQuantidadeDTO;

public class OSDao {
	private static Connection conn = (new ConexaoBD()).getInstance();
	
	public static List<OS> getAll() throws SQLException {
        String sql = "select * from ordem_servico ORDER BY id_os;";
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

	public static Long criarOS(CadastrarOSDTO novaOS) throws SQLException {
		Long idOS = null;
	    try {
	        conn.setAutoCommit(false);
	        
	        String sqlOS = "INSERT INTO ordem_servico (data, valor_total, etapa, placa, id_cliente) " +
	                      "VALUES (?, ?, 'Orcamento', ?, ?) RETURNING id_os;";
	        
	        PreparedStatement psOS = conn.prepareStatement(sqlOS);
	        Double valor_total_os = 0.;
	        for (ColaboradorServicoDTO colaboradorServico: novaOS.getServicos()) {
	        	valor_total_os += ServicoDao.getById(colaboradorServico.getId_servico()).getValor() * colaboradorServico.getQuantidade();
	        }
	        for (PecaQuantidadeDTO pecaQuantidade: novaOS.getPecas()) {
	        	valor_total_os += PecaDao.getById(pecaQuantidade.getId_peca()).getValorUnitario() * pecaQuantidade.getQuantidade();
	        }
	        psOS.setDate(1, new Date(System.currentTimeMillis()));
	        psOS.setDouble(2, valor_total_os);
	        psOS.setString(3, novaOS.getPlaca());
	        psOS.setLong(4, VeiculoDao.getByPlaca(novaOS.getPlaca()).getProprietarioMaisRecente().getId());	        
	        
	        ResultSet rsOS = psOS.executeQuery();
	        if (!rsOS.next()) {
	            throw new SQLException("Failed to create ordem_servico record");
	        }
	        idOS = rsOS.getLong("id_os");
	        
	        if (novaOS.getServicos() != null) {
	            String sqlServico = "INSERT INTO item_servico (id_os, id_servico, cpf_colaborador ,valor_unitario, data_inicio, quantidade, valor_total) " +
	                              "VALUES (?, ?, ?, ?, ?, ?, ?);";
	            PreparedStatement psServico = conn.prepareStatement(sqlServico);
	            for (ColaboradorServicoDTO servico : novaOS.getServicos()) {
	                psServico.setLong(1, idOS);
	                psServico.setLong(2, servico.getId_servico());
	                psServico.setString(3, servico.getCpf_colaborador());
	                Double valor_unitario = ServicoDao.getById(servico.getId_servico()).getValor();
	                psServico.setDouble(4, valor_unitario);
	                psServico.setDate(5, new Date(System.currentTimeMillis()));
	                psServico.setInt(6, servico.getQuantidade());
	                psServico.setDouble(7, servico.getQuantidade() * valor_unitario);
	                psServico.executeUpdate();
	            }
	        }
	        
	        if (novaOS.getPecas() != null) {
	            String sqlPeca = "INSERT INTO item_peca (id_os, id_peca, quantidade, valor_total, valor_unitario) " +
	                           "VALUES (?, ?, ?, ?, ?);";
	            PreparedStatement psPeca = conn.prepareStatement(sqlPeca);
	            for (PecaQuantidadeDTO peca : novaOS.getPecas()) {
	            	Double valor_unitario = PecaDao.getById(peca.getId_peca()).getValorUnitario();
	                psPeca.setLong(1, idOS);
	                psPeca.setLong(2, peca.getId_peca());
	                psPeca.setInt(3, peca.getQuantidade());
	                psPeca.setDouble(4, valor_unitario * peca.getQuantidade());
	                psPeca.setDouble(5, valor_unitario);
	                psPeca.executeUpdate();
	            }
	        }

	        conn.commit();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	        if (conn != null) {
	            try {
	                conn.rollback();
	                return null;
	            } catch (SQLException ex) {
	                throw new SQLException("Error rolling back transaction", ex);
	            }
	        }
	        throw e;
	    }
	    conn.setAutoCommit(true);
	    return idOS;	
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}

	public static String mudarEtapa(Long id, String etapa) throws SQLException {
	    String sql = "UPDATE ordem_servico SET etapa = ? WHERE id_os = ?";
	    
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    
	    stmt.setString(1, etapa);
	    stmt.setLong(2, id);
	    
	    int rowsAffected = stmt.executeUpdate();
	    
	    if (rowsAffected > 0) {
	        return null;
	    } else {
	        return "Ordem de serviço não encontrada.";
	    }
	}

	public static String completarServico(Long idOs, Long idServico, boolean terminar) throws SQLException {
		OS os = OSDao.getById(idOs);
		ItemServico itemServico = null;		
		
		if (os == null) {
			return "OS não encontrada";
		}
		
		for (ItemServico item: os.getItensServico()) {
			if (idServico.equals(item.getServico().getId())) {
				itemServico = item;
				break;
			}
		}
		
		if (itemServico == null) {
			return "Serviço não encontrado";
		}
		
		if (terminar == (itemServico.getDataFim() != null)) {
			return null; //nesse caso ja esta no estado que o usuario quer que esteja
		}
		
		if (terminar) {
			String status = ItemServicoDao.terminarServico(idServico);
			boolean todasCompletas = true;
			for (ItemServico item: OSDao.getById(idOs).getItensServico()) {
				if (item.getDataFim() == null) {
					todasCompletas = false;
					break;
				}
			}
			if (todasCompletas) {
				mudarEtapa(idOs, "Finalizado");
			}
			return status;
		} else if (!terminar) {
			return ItemServicoDao.retomarServico(idServico);
		}
		return "Nenhuma alteração realizada";
		
	}

}
