package com.pi.autogyn.persistencia.entidades;

public enum Etapa {
//	'Orcamento', 'Aprovado', 'Execucao', 'Finalizado', 'Pago'
	ORCAMENTO("Orçamento"),
	APROVADO("Aprovado"),
	EXECUCAO("Execucão"),
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
