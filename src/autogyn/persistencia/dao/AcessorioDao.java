package autogyn.persistencia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import autogyn.persistencia.entidades.Acessorio;
import autogyn.persistencia.entidades.Servico;
import autogyn.persistencia.ferramentas.ConexaoBD;
import autogyn.persistencia.ferramentas.EasyQuery;

public class AcessorioDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Acessorio> getAll() throws SQLException {
        String sql = "select * from acessorio;";
        List<Acessorio> acessorios = new LinkedList<>();
        System.out.println(conn);
        ResultSet rs = EasyQuery.exec(conn, sql);
        while(rs.next()) {
            acessorios.add(new Acessorio(rs));
        }
        return acessorios;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
}
