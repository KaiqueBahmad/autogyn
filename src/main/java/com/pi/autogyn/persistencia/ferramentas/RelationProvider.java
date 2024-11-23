package com.pi.autogyn.persistencia.ferramentas;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RelationProvider {
	public ResultSet provide() throws SQLException;
}
