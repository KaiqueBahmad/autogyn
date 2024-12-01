package com.pi.autogyn.validacoes;

public enum StatusValidacao {
	OK("ok"),
	CPF_INVALIDO("CPF inserido é invalido"),
	CNPJ_INVALIDO("CNPJ inserido é invalido"),
	CPF_TAMANHO_ERRADO("CPF deve ter 11 digitos"),
	CNPJ_TAMANHO_ERRADO("CNPJ deve ter 14 digitos"),
	EMAIL_INVALIDO("Email inserido é invalido"),
	DDD_INVALIDO("DDD inserido é invalido"),
	TELEFONE_INVALIDO("Número de telefone inserido é invalido"),
	CEP_INVALIDO("CEP inserido é inválido"),
	UF_INVALIDA("UF inserida é invalida"),
	IE_INVALIDA("Inscrição Estadual inserida é invalida"),
	NC_INVALIDO("Número de chassi inserido é invalido"),
	PLACA_INVALIDA("Placa inserida é invalida");
	
	private String mensagem;
	private StatusValidacao(String msg) {
		this.mensagem = msg;
	}
	public String getMensagem() {
		return this.mensagem;
	}
	
}
