package com.pi.autogyn.servicos;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pi.autogyn.persistencia.dao.OSDao;
import com.pi.autogyn.persistencia.dao.PecaDao;
import com.pi.autogyn.persistencia.dao.ServicoDao;
import com.pi.autogyn.persistencia.entidades.Etapa;
import com.pi.autogyn.persistencia.entidades.ItemPeca;
import com.pi.autogyn.persistencia.entidades.OS;
import com.pi.autogyn.servicos.dto.CadastrarOSDTO;
import com.pi.autogyn.servicos.dto.CadastrarServicoDTO;
import com.pi.autogyn.servicos.dto.OSDTO;
import com.pi.autogyn.servicos.dto.OrdemServicoListaDTO;

public class OSService {
	public static List<OrdemServicoListaDTO> listarOSCadastradas() throws SQLException {
		List<OrdemServicoListaDTO> lista = new LinkedList<>();
		for (OS os: OSDao.getAll()) {
			lista.add(new OrdemServicoListaDTO(os));
		}
		return lista;
	}

	public static OSDTO getOs(Long idOs) throws SQLException {
		return new OSDTO(OSDao.getById(idOs));
	}
	
	public static Long criarOS(CadastrarOSDTO novaOS) throws SQLException {
		Long idOs = OSDao.criarOS(novaOS);
		
		if (idOs != null && idOs > 0) {
			retirarEstoque(idOs);
		}
		
		return idOs;
	}

	private static void retirarEstoque(Long idOs) throws SQLException {
		OS os = OSDao.getById(idOs);
		for (ItemPeca item: os.getItensPeca()) {
			PecaDao.addQuantidade(item.getPeca().getId(), -item.getQuantidade());
		}
	}
	
	private static void devolverEstoque(Long idOs) throws SQLException {
		OS os = OSDao.getById(idOs);
		for (ItemPeca item: os.getItensPeca()) {
			PecaDao.addQuantidade(item.getPeca().getId(), item.getQuantidade());
		}
	}
	

	public static String aprovarOS(Long id) throws SQLException {
		OS os = OSDao.getById(id);
		if (os.getEtapa() != Etapa.ORCAMENTO) {
			return "ETAPA deve ser Orçamento para poder ser aprovada. Nao"+os.getEtapa();
		}
		if (os.getItensServico() == null || os.getItensServico().size() == 0) {
			return OSDao.mudarEtapa(id, "Finalizado");
		}
		return OSDao.mudarEtapa(id, "Aprovado");
	}
	
	public static String iniciarExecucaoOS(Long id) throws SQLException {
		OS os = OSDao.getById(id);
		if (os.getEtapa() != Etapa.APROVADO) {
			return "ETAPA deve ser 'Aprovada' para poder entrar em execução. Nao"+os.getEtapa();
		}
		return OSDao.mudarEtapa(id, "Execucao");
	}

	public static String cancelarOS(Long idOs) throws SQLException {
		String status = OSDao.mudarEtapa(idOs, null);
		if (status != null) {
			return status;
		}
		devolverEstoque(idOs);
		return null;
		
	}

	public static String pagarOS(Long idOs, Double valorPago) throws SQLException {
		if (OSDao.getById(idOs) == null) {
			return "OS não encontrada";
		}
		
		if (valorPago == null) {
			return "Valor pago não pode ser nulo";
			
		}
		if (valorPago <= 0) {
			return "Valor pago deve ser maior do que zero";
		}
		
		String status1 = OSDao.pagar(idOs, valorPago);
		if (status1 != null) {
			return status1;
		}
		
		return OSDao.mudarEtapa(idOs, "Pago");
		
	}
	
}
