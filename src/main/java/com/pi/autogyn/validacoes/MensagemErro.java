package com.pi.autogyn.validacoes;

import java.util.LinkedList;
import java.util.List;

public class MensagemErro {
	private List<String> mensagens = new LinkedList<>();
	
	public MensagemErro() {
		
	}
	public MensagemErro(String mensagem) {
		mensagens.add(mensagem);
	}
	public MensagemErro(StatusValidacao status) {
		mensagens.add(status.getMensagem());
	}
	public MensagemErro(List<StatusValidacao> erros) {
		for (StatusValidacao erro: erros) {
			mensagens.add(erro.getMensagem());
		}
	}
	public List<String> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	
}
