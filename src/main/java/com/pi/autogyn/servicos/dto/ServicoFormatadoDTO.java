package com.pi.autogyn.servicos.dto;

import com.pi.autogyn.persistencia.entidades.Servico;

public class ServicoFormatadoDTO {
	private Long id;
	private String formatado;

	
	public ServicoFormatadoDTO() {
		
	}
	
	public ServicoFormatadoDTO(Servico servico) {
		this.id = servico.getId();
		this.formatado = String.format("%s | %s", servico.getDescricao(), FormatacoesComuns.doubleToBRL(servico.getValor()));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFormatado() {
		return formatado;
	}
	public void setFormatado(String formatado) {
		this.formatado = formatado;
	}
	
	
	
}
