package com.pi.autogyn.servicos;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.dao.MarcaDao;
import com.pi.autogyn.persistencia.dao.ModeloDao;
import com.pi.autogyn.persistencia.dao.VeiculoDao;
import com.pi.autogyn.persistencia.entidades.Marca;
import com.pi.autogyn.persistencia.entidades.Veiculo;
import com.pi.autogyn.servicos.dto.MarcaListaCadastroDTO;
import com.pi.autogyn.servicos.dto.VeiculoListaCadastradosDto;

public class VeiculoService {
	public static List<VeiculoListaCadastradosDto> listarVeiculosCadastrados() throws SQLException {
		List<VeiculoListaCadastradosDto> lista = new LinkedList<>();
		for (Veiculo veiculo: VeiculoDao.getAll()) {
			lista.add(new VeiculoListaCadastradosDto(veiculo));
		}
		return lista;
	}
	
	public static List<MarcaListaCadastroDTO> listarMarcasCadastradas() throws SQLException {
		List<MarcaListaCadastroDTO> lista = new LinkedList<>();
		for (Marca marca: MarcaDao.getAll()) {
			lista.add(new MarcaListaCadastroDTO(marca));
		}
		return lista;
	}
	
	public static boolean insertMarca(String nomeModelo) throws SQLException {
		return MarcaDao.insert(nomeModelo);
	}
	
	public static boolean insertModelo(Long idMarca, String nomeModelo) throws SQLException {
		return ModeloDao.insert(idMarca, nomeModelo);
	}
	
}
