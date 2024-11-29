package com.pi.autogyn.persistencia.ferramentas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBD {
	private static String url = "jdbc:postgresql://localhost:5432/postgres";
	private static String user = "postgres";
	private static String password = "Heloisa-2005";
	private static Connection connection = null;
	
	public static Connection getInstance() {
		if (connection == null) {
			try {
				ConexaoBD.createConnection();				
			} catch (Exception e) {
				e.getStackTrace();
				return connection;
			}
		}
		return connection;
	}
	
	public static void createConnection() throws SQLException {
		Properties props = new Properties();
		props.setProperty("user", user);
		props.setProperty("password", password);
		connection = DriverManager.getConnection(url, props);
	}
	
	public static void main(String[] args) throws SQLException {
		createConnection();
	}
	
}
