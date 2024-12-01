package com.pi.autogyn.servicos.dto;

public class ColaboradorServicoDTO {
	private Long id_servico;
	private String cpf_colaborador;
	private Integer quantidade;
	
	
	
	public Long getId_servico() {
		return id_servico;
	}
	public void setId_servico(Long id_servico) {
		this.id_servico = id_servico;
	}
	public String getCpf_colaborador() {
		return cpf_colaborador;
	}
	public void setCpf_colaborador(String cpf_colaborador) {
		this.cpf_colaborador = cpf_colaborador;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
}
