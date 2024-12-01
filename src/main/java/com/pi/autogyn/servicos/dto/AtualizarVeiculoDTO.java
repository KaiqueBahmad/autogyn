package com.pi.autogyn.servicos.dto;

public class AtualizarVeiculoDTO {
	private Integer quilometragem;
	private Long id_novo_proprietario;
	
	public Integer getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(Integer quilometragem) {
		this.quilometragem = quilometragem;
	}
	public Long getId_novo_proprietario() {
		return id_novo_proprietario;
	}
	public void setId_novo_proprietario(Long id_novo_proprietario) {
		this.id_novo_proprietario = id_novo_proprietario;
	}
	
}
