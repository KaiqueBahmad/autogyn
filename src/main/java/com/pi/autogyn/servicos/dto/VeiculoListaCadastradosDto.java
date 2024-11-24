package com.pi.autogyn.servicos.dto;

import com.pi.autogyn.persistencia.entidades.Cliente;
import com.pi.autogyn.persistencia.entidades.Modelo;
import com.pi.autogyn.persistencia.entidades.Veiculo;

public class VeiculoListaCadastradosDto {
	public String nomeCliente = "";
	public String placa;
	public String marca;
	public String modelo;
	public int anoModelo;
	public int anoFabricacao;
	
	public VeiculoListaCadastradosDto(Veiculo veiculo) {
		Cliente proprietario = veiculo.getProprietarioMaisRecente();
		if (proprietario != null) {
			this.nomeCliente = proprietario.getNome();
		}
		this.placa = veiculo.getPlaca();
		Modelo modelo = veiculo.getModelo();
		this.modelo = modelo.getNome();
		this.marca = modelo.getMarca().getNome();
		this.anoModelo = veiculo.getAnoModelo();
		this.anoFabricacao = veiculo.getAnoFabricacao();
	}
	
}
