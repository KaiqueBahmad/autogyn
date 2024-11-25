package com.pi.autogyn.servicos.dto;

public class CadastrarClienteDTO {
	private String nome;
	private String email;
	
	private boolean isPJ;

	private String cpf;	
	private String cnpj;

	private String inscricao_estadual;
	private String nomeContato;
	
	private int ddd;
	private int ddd2;
	private int telefone;
	private int telefone2;	
	private String cep;
	private String cidade;
	private String uf;
	private String numero;
	private String logradouro;
	private String complemento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPJ() {
		return isPJ;
	}
	public void setPJ(boolean isPJ) {
		this.isPJ = isPJ;
	}
	public int getDdd() {
		return ddd;
	}
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	public int getDdd2() {
		return ddd2;
	}
	public void setDdd2(int ddd2) {
		this.ddd2 = ddd2;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public int getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(int telefone2) {
		this.telefone2 = telefone2;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricao_estadual() {
		return inscricao_estadual;
	}
	public void setInscricao_estadual(String inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}
	public String getNomeContato() {
		return nomeContato;
	}
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}
	
	
	
}
