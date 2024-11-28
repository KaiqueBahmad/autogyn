package com.pi.autogyn.servicos.dto;

import java.sql.SQLException;

import com.pi.autogyn.persistencia.dao.AcessorioDao;

public class AcessorioService {
	public static boolean criarProduto(String nomeModelo) throws SQLException {
		return AcessorioDao.insert(nomeModelo);
	}
}
