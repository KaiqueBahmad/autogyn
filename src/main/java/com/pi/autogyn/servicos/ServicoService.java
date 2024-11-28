package com.pi.autogyn.servicos;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.dao.ServicoDao;
import com.pi.autogyn.persistencia.entidades.Servico;
import com.pi.autogyn.servicos.dto.CadastrarServicoDTO;
import com.pi.autogyn.servicos.dto.ServicoFormatadoDTO;

public class ServicoService {
	public static boolean cadastrarServico(CadastrarServicoDTO servico) throws SQLException {
		return ServicoDao.insert(servico.getDescricao(), servico.getValor());
	}
	
	public static List<ServicoFormatadoDTO>	buscarServicosFormatados() throws SQLException {
		List<ServicoFormatadoDTO> lista = new LinkedList<>();
		for (Servico servico: ServicoDao.getAll()) {
			lista.add(new ServicoFormatadoDTO(servico));
		}
		return lista;
	}
	
}
