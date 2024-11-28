package com.pi.autogyn.servicos.dto;

import com.pi.autogyn.persistencia.entidades.Peca;

public class PecaListaDTO {
	private Long id;
	private String codigo;
	private String descricao;
	private String sku;
	private int quantidadeEstoque;
	private double valorUnitario;
	
		
	
	public PecaListaDTO() {

	}
	
	public PecaListaDTO(Peca peca) {
		this.id = peca.getId();
		this.codigo = peca.getCodigo();
		this.descricao = peca.getDescricao();
		this.sku = peca.getSku();
		this.quantidadeEstoque = peca.getQuantidadeEstoque();
		this.valorUnitario = peca.getValorUnitario();
	}
		
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
}
