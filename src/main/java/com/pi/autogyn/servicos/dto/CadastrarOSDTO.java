package com.pi.autogyn.servicos.dto;

import java.util.List;

public class CadastrarOSDTO {
	private String placa;
	private List<ColaboradorServicoDTO> servicos;
	private List<PecaQuantidadeDTO> pecas;
	
	
	public List<ColaboradorServicoDTO> getServicos() {
		return servicos;
	}
	public void setServicos(List<ColaboradorServicoDTO> servicos) {
		this.servicos = servicos;
	}
	public List<PecaQuantidadeDTO> getPecas() {
		return pecas;
	}
	public void setPecas(List<PecaQuantidadeDTO> pecas) {
		this.pecas = pecas;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	
	
}
