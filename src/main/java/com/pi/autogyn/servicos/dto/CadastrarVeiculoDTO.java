package com.pi.autogyn.servicos.dto;

import java.util.LinkedList;
import java.util.List;

public class CadastrarVeiculoDTO {
	private String placa;
	private Long id_cliente;
	private Integer ano_fabricacao;
	private Integer ano_modelo;
	private Long id_modelo;
	private Integer quilometragem;
	private String numero_chassi;
	private Integer numero_patrimonio;
	
	private List<Long> acessorios = new LinkedList<>();

	
	
	@Override
	public String toString() {
		return "CadastrarVeiculoDTO [placa=" + placa + ", id_cliente=" + id_cliente + ", ano_fabricacao="
				+ ano_fabricacao + ", ano_modelo=" + ano_modelo + ", id_modelo=" + id_modelo
				+ ", quilometragem=" + quilometragem + ", numero_chassi=" + numero_chassi + ", numero_patrimonio="
				+ numero_patrimonio + ", acessorios=" + acessorios + "]";
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Integer getAno_fabricacao() {
		return ano_fabricacao;
	}

	public void setAno_fabricacao(Integer ano_fabricacao) {
		this.ano_fabricacao = ano_fabricacao;
	}

	public Integer getAno_modelo() {
		return ano_modelo;
	}

	public void setAno_modelo(Integer ano_modelo) {
		this.ano_modelo = ano_modelo;
	}

	public Long getId_modelo() {
		return id_modelo;
	}

	public void setId_modelo(Long id_modelo) {
		this.id_modelo = id_modelo;
	}

	public Integer getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Integer quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getNumero_chassi() {
		return numero_chassi;
	}

	public void setNumero_chassi(String numero_chassi) {
		this.numero_chassi = numero_chassi;
	}

	public Integer getNumero_patrimonio() {
		return numero_patrimonio;
	}

	public void setNumero_patrimonio(Integer numero_patrimonio) {
		this.numero_patrimonio = numero_patrimonio;
	}

	public List<Long> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<Long> acessorios) {
		this.acessorios = acessorios;
	}
	
}
