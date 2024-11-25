package com.pi.autogyn.servicos;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.dao.MarcaDao;
import com.pi.autogyn.persistencia.dao.VeiculoDao;
import com.pi.autogyn.persistencia.entidades.Veiculo;
import com.pi.autogyn.servicos.dto.VeiculoListaCadastradosDto;

public class VeiculoService {
	public static List<VeiculoListaCadastradosDto> listarVeiculosCadastrados() throws SQLException {
		List<VeiculoListaCadastradosDto> lista = new LinkedList<>();
		for (Veiculo veiculo: VeiculoDao.getAll()) {
			lista.add(new VeiculoListaCadastradosDto(veiculo));
		}
		return lista;
	}
	
	public static boolean insertMarca(String nome) throws SQLException {
		return MarcaDao.insert(nome);
	}
}
