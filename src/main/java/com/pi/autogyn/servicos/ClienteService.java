package com.pi.autogyn.servicos;

import java.sql.SQLException;

import com.pi.autogyn.persistencia.dao.ClienteDao;
import com.pi.autogyn.persistencia.entidades.Cliente;
import com.pi.autogyn.servicos.dto.CadastrarClienteDTO;

public class ClienteService {
	public static Long inserirCliente(CadastrarClienteDTO novoCliente) throws SQLException {
		return ClienteDao.insert(new Cliente(novoCliente));
	}
}
