package com.pi.autogyn.validacoes;

public enum StatusValidacao {
	OK("ok"),
	CPF_INVALIDO("Cpf inserido é invalido"),
	CNPJ_INVALIDO("CNPJ inserido é invalido"),
	CPF_TAMANHO_ERRADO("CPF deve ter 11 digitos");
	
	private String mensagem;
	private StatusValidacao(String msg) {
		this.mensagem = msg;
	}
	public String getMensagem() {
		return this.mensagem;
	}
	
}
