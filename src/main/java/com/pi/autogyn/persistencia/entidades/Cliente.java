package com.pi.autogyn.persistencia.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.pi.autogyn.persistencia.dao.PropriedadeDao;
import com.pi.autogyn.servicos.dto.CadastrarClienteDTO;

public class Cliente {
	public Cliente(ResultSet rs) throws SQLException {
		this.id = rs.getLong("id_cliente");
		this.nome = rs.getString("nome");
		this.email = rs.getString("email");
		this.endereco = new Endereco(rs);
		this.telefone = new Telefone(rs);
		this.telefone2 = Telefone.createSecondary(rs);
		this.pessoaJuridica = PJ.create(rs);
		this.pessoaFisica = PF.create(rs);
		this.lazyload = true;
	}
	
	private boolean lazyload = false;
	public void setLazyload(boolean ligado) {
		this.lazyload = ligado;
	}
	
	private Long id;
	private String nome;
	private String email;
	private Endereco endereco;
	private Telefone telefone;
	private Optional<Telefone> telefone2;
	private Optional<PJ> pessoaJuridica;
	private Optional<PF> pessoaFisica;
	private List<Propriedade> propriedades;

	
	public Cliente(CadastrarClienteDTO dto) {
	    this.nome = dto.getNome();
	    this.email = dto.getEmail();
	    this.lazyload = false;
	    
	    this.endereco = new Endereco();
	    this.endereco.setLogradouro(dto.getLogradouro());
	    this.endereco.setComplemento(dto.getComplemento());
	    this.endereco.setNumero(dto.getNumero());
	    this.endereco.setCep(dto.getCep());
	    this.endereco.setCidade(dto.getCidade());
	    this.endereco.setUf(dto.getUf());
	    
	    this.telefone = new Telefone();
	    this.telefone.setDdd(dto.getDdd());
	    this.telefone.setTelefone(dto.getTelefone());
	    
	    if (dto.getDdd2() != 0 && dto.getTelefone2() != 0) {
	        Telefone tel2 = new Telefone();
	        tel2.setDdd(dto.getDdd2());
	        tel2.setTelefone(dto.getTelefone2());
	        this.telefone2 = Optional.of(tel2);
	    } else {
	        this.telefone2 = Optional.empty();
	    }
	    
	    if (dto.isPJ()) {
	        PJ pj = new PJ();
	        pj.setCnpj(dto.getCnpj());
	        pj.setContato(dto.getNomeContato());
	        pj.setInscricaoEstadual(dto.getInscricao_estadual());
	        this.pessoaJuridica = Optional.of(pj);
	        this.pessoaFisica = Optional.empty();
	    } else {
	        PF pf = new PF();
	        pf.setCpf(dto.getCpf());
	        this.pessoaFisica = Optional.of(pf);
	        this.pessoaJuridica = Optional.empty();
	    }

	    
	    this.propriedades = null;
	}
	
	
	public List<Propriedade> getPropriedades() {
		if (this.propriedades == null && lazyload) {
			try {
				this.propriedades = PropriedadeDao.getAllByCliente(this.id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.propriedades;
	}

	
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco + ", telefone="
				+ telefone + ", telefone2=" + telefone2 + ", pessoaJuridica=" + pessoaJuridica + ", pessoaFisica="
				+ pessoaFisica + ", propriedades=" + getPropriedades() + "]";
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



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



	public Endereco getEndereco() {
		return endereco;
	}



	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}



	public Telefone getTelefone() {
		return telefone;
	}



	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}



	public Optional<Telefone> getTelefone2() {
		return telefone2;
	}



	public void setTelefone2(Optional<Telefone> telefone2) {
		this.telefone2 = telefone2;
	}



	public Optional<PJ> getPessoaJuridica() {
		return pessoaJuridica;
	}



	public void setPessoaJuridica(Optional<PJ> pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}



	public Optional<PF> getPessoaFisica() {
		return pessoaFisica;
	}



	public void setPessoaFisica(Optional<PF> pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}



	public void setPropriedades(List<Propriedade> propriedades) {
		this.propriedades = propriedades;
	}
	
	
	
	
	
	
}