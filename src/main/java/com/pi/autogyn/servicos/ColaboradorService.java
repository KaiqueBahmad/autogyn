package com.pi.autogyn.servicos;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.dao.ColaboradorDao;
import com.pi.autogyn.persistencia.entidades.Colaborador;
import com.pi.autogyn.servicos.dto.CadastrarColaboradorDTO;
import com.pi.autogyn.servicos.dto.ColaboradorDTO;

public class ColaboradorService {
	public static List<ColaboradorDTO> listarTodos() throws SQLException {
		List<ColaboradorDTO> lista = new LinkedList<>();
		for (Colaborador colaborador: ColaboradorDao.getAll()) {
			lista.add(new ColaboradorDTO(colaborador));
		}
		return lista;
	}
	
	public static String incluirColaborador(CadastrarColaboradorDTO novoColaborador) throws SQLException {
		
		return ColaboradorDao.insert(novoColaborador.getCpf(), novoColaborador.getNome());
	}
	
}
