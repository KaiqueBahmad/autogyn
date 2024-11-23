package com.pi.autogyn.persistencia.ferramentas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EasyQuery {
	public static ResultSet exec(Connection conn,String query) throws SQLException {
		Statement statement = conn.createStatement();
        return statement.executeQuery(query);
	}
}
