package com.pi.autogyn.servicos;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.dao.VeiculoDao;
import com.pi.autogyn.persistencia.entidades.Veiculo;
import com.pi.autogyn.servicos.dto.VeiculoListaCadastroDto;

public class VeiculoService {
	public static List<VeiculoListaCadastroDto> listarVeiculosCadastrados() throws SQLException {
		List<VeiculoListaCadastroDto> lista = new LinkedList<>();
		for (Veiculo veiculo: VeiculoDao.getAll()) {
			lista.add(new VeiculoListaCadastroDto(veiculo));
		}
		return lista;
	}
}
