package autogyn.persistencia.dao;

import java.util.LinkedList;
import java.util.List;

import autogyn.persistencia.entidades.Marca;
import autogyn.persistencia.entidades.Servico;
import autogyn.persistencia.ferramentas.ConexaoBD;
import autogyn.persistencia.ferramentas.EasyQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MarcaDao {
	private static Connection conn = ConexaoBD.getInstance();
	
	public static List<Marca> getAll() throws SQLException {
        String sql = "select * from marca;";
        List<Marca> marcas = new LinkedList<>();
        ResultSet rs = EasyQuery.exec(conn, sql);
        while(rs.next()) {
        	Marca marca = new Marca(rs);
        	marca.setProviderFor(Marca.Relacionamentos.Modelos, () -> {
        		return EasyQuery.exec(conn, "select * from modelo where id_marca = "+marca.getId()+";");
        	});
        	marcas.add(marca);
        	
        }
        return marcas;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getAll());
	}
	
}
