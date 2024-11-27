package com.pi.autogyn.servicos;

import java.sql.SQLException;

import com.pi.autogyn.persistencia.dao.ClienteDao;
import com.pi.autogyn.persistencia.entidades.Cliente;
import com.pi.autogyn.servicos.dto.CadastrarClienteDTO;

public class ClienteService {
	public static Long inserirCliente(CadastrarClienteDTO novoCliente) throws SQLException {
	    if (novoCliente.getDdd() < 1 || novoCliente.getDdd() > 999) {
            return null; // Retorna null se o DDD estiver fora do range
        }
	    if (novoCliente.getDdd2() != null && novoCliente.getDdd2() < 1 || novoCliente.getDdd2() > 999) {
            return null; // Retorna null se o DDD estiver fora do range
        }
		return ClienteDao.insert(new Cliente(novoCliente));
	}
}
