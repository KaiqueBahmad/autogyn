package com.pi.autogyn.servicos;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.dao.OSDao;
import com.pi.autogyn.persistencia.dao.ServicoDao;
import com.pi.autogyn.persistencia.entidades.OS;
import com.pi.autogyn.servicos.dto.CadastrarServicoDTO;
import com.pi.autogyn.servicos.dto.OrdemServicoListaDTO;

public class OSService {
	public static List<OrdemServicoListaDTO> listarOSCadastradas() throws SQLException {
		List<OrdemServicoListaDTO> lista = new LinkedList<>();
		for (OS os: OSDao.getAll()) {
			lista.add(new OrdemServicoListaDTO(os));
		}
		return lista;
	}
}
