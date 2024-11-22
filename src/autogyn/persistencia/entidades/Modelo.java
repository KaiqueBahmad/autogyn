package autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {
	private Long id;
	private String nome;
	
	public Modelo(ResultSet rs) throws SQLException {
		this.id = rs.getLong("id_modelo");
		this.nome = rs.getString("nome");
	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", nome=" + nome + "]";
	}
	
	

}
