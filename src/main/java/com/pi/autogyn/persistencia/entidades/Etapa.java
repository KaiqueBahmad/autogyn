package com.pi.autogyn.persistencia.entidades;

public enum Etapa {
//	'Orcamento', 'Aprovado', 'Execucao', 'Finalizado', 'Pago'
	ORCAMENTO("Orcamento"),
	APROVADO("Aprovado"),
	EXECUCAO("Execucao"),
	FINALIZADO("Finalizado"),
	PAGO("Pago");

	private String etapa;
	Etapa(String etapa) {
		this.etapa = etapa;
	}
	public String valor() {
		return this.etapa;		
	}
}
