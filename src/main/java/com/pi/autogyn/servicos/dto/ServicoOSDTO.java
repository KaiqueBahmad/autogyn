package com.pi.autogyn.servicos.dto;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import com.pi.autogyn.persistencia.dao.OSDao;
import com.pi.autogyn.persistencia.entidades.ItemServico;
import com.pi.autogyn.persistencia.entidades.Servico;

public class ServicoOSDTO {
	private Long id;
	private String nome;
	private Boolean terminado;
	private String inicio;
	private String fim;
	private String responsavel;
	
	public ServicoOSDTO() {
	}
	
	public ServicoOSDTO(ItemServico itemServico) {
	    Servico servico = itemServico.getServico();
	    this.id = servico.getId();
	    this.nome = servico.getDescricao();
	    this.terminado = itemServico.getDataFim() != null;
	    this.inicio = itemServico.getDataInicio() != null ? 
	        new java.text.SimpleDateFormat("dd/MM/yyyy")
	            .format(itemServico.getDataInicio()) : null;
	    this.fim = itemServico.getDataFim() != null ? 
	        new java.text.SimpleDateFormat("dd/MM/yyyy")
	            .format(itemServico.getDataFim()) : null;
	    this.responsavel = itemServico.getCpfColaborador();
	}
	
	public static void main(String args[]) throws SQLException {
		for (ItemServico item: OSDao.getAll().get(10).getItensServico()) {
			System.out.println(new ServicoOSDTO(item));
		}
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getTerminado() {
		return terminado;
	}

	public void setTerminado(Boolean terminado) {
		this.terminado = terminado;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}
	@Override
	public String toString() {
		return "ServicoOSDTO [id=" + id + ", nome=" + nome + ", terminado=" + terminado + ", inicio=" + inicio
				+ ", fim=" + fim + ", responsavel=" + responsavel + "]";
	}
	
	
	
	
}
