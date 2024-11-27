package com.pi.autogyn.servicos;

import java.sql.SQLException;

import com.pi.autogyn.persistencia.dao.ServicoDao;
import com.pi.autogyn.servicos.dto.CadastrarServicoDTO;

public class ServicoService {
	public static boolean cadastrarServico(CadastrarServicoDTO servico) throws SQLException {
		return ServicoDao.insert(servico.getDescricao(), servico.getValor());
	}
}
