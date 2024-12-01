package com.pi.autogyn.servicos.dto;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pi.autogyn.persistencia.dao.OSDao;
import com.pi.autogyn.persistencia.entidades.Etapa;
import com.pi.autogyn.persistencia.entidades.ItemServico;
import com.pi.autogyn.persistencia.entidades.OS;

public class OSDTO {
	private Long id;
	private String cliente;
	private Double valor_total;
	private String etapa;
	private List<ServicoOSDTO> servicos = new LinkedList<>();
	
	public OSDTO() {
		
	}
	public OSDTO(OS os) {
		this.id = os.getId();
		this.etapa = os.getEtapa().name();
		System.out.println(this.etapa);
		if (os.getCliente().getPessoaFisica().isPresent()) {
	    	this.cliente = String.format(
				"[%s] %s",
				os.getCliente().getPessoaFisica().get().getCpf(),
				os.getCliente().getNome()
			);
	    } else if (os.getCliente().getPessoaJuridica().isPresent()) {
	    	this.cliente = String.format(
				"[%s] %s",
				os.getCliente().getPessoaJuridica().get().getCnpj(),
				os.getCliente().getNome()
			);
	    } else if (os.getCliente() != null) {
	    	this.cliente = os.getCliente().getNome();
	    } else {
	    	this.cliente = "[Cliente não encontrado]";
	    }
		this.valor_total = os.getValor_total();
		for (ItemServico item: os.getItensServico()) {
			servicos.add(new ServicoOSDTO(item));
		}
	}
	
	public static void main(String[] args) throws SQLException {
		for (OS os: OSDao.getAll()) {
			System.out.println(new OSDTO(os));
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Double getValor_total() {
		return valor_total;
	}
	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	public List<ServicoOSDTO> getServicos() {
		return servicos;
	}
	public void setServicos(List<ServicoOSDTO> servicos) {
		this.servicos = servicos;
	}
	
	
	
	@Override
	public String toString() {
		return "OSDTO [id=" + id + ", cliente=" + cliente + ", valor_total=" + valor_total + ", etapa=" + etapa
				+ ", servicos=" + servicos + "]";
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	
	
	
	
	
}
