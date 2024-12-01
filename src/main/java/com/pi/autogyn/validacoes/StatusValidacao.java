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
	PLACA_INVALIDA("Placa inserida é invalida"),
	VEICULO_NAO_ENCONTRADO("Veiculo não encontrado"),
	SEM_CPF_COLABORADOR("Faltando CPF do colaborador na requisição"),
	COLABORADOR_NAO_ENCONTRADO("Colaborador não foi encontrado"),
	SEM_ID_SERVICO("Sem id serviço na requisição"),
	SERVICO_NAO_ENCONTRADO("ID de servico inserido não foi encontrado no banco de dados"),
	ORCAMENTO_SEM_VALOR("Insira um valor para o orçamento."),
	QUANTIDADE_PECAS_NULA("Quantidade de Peças é nula"),
	QUANTIDADE_PECAS_NEGATIVA("Insira uma quantidade positiva e maior que zero de peças"),
	SEM_ID_PECA("Sem ID de peça"),
	PECA_NAO_ENCONTRADA("Peça não encontrada"),
	QUANTIDADE_SERVICO_NULA("Quantidade de serviço não inserida"),
	QUANTIDADE_SERVICO_NEGATIVA("Insira uma quantidade positiva e maior que zero de Serviços");
	
	private String mensagem;
	private StatusValidacao(String msg) {
		this.mensagem = msg;
	}
	public String getMensagem() {
		return this.mensagem;
	}
	
}
