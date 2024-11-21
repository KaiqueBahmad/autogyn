package autogyn.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Servico {
	
	private Long id;
	private String descricao;
	private Double valor;
	
	public Servico() {
		
	}
	
	public Servico(ResultSet queryResponse) throws SQLException {
		this.id = queryResponse.getLong("id_servico");
		this.descricao = queryResponse.getString("descricao");
		this.valor = queryResponse.getDouble("valor");
	}

	@Override
	public String toString() {
		return "Servico [id=" + id + ", descricao=" + descricao + ", valor=" + valor + "]";
	}
	
	
	
}
