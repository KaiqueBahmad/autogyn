package com.pi.autogyn.validacoes;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.pi.autogyn.persistencia.dao.ColaboradorDao;
import com.pi.autogyn.persistencia.dao.PecaDao;
import com.pi.autogyn.persistencia.dao.ServicoDao;
import com.pi.autogyn.persistencia.dao.VeiculoDao;
import com.pi.autogyn.persistencia.entidades.Peca;
import com.pi.autogyn.servicos.dto.CadastrarAcessorioDTO;
import com.pi.autogyn.servicos.dto.CadastrarClienteDTO;
import com.pi.autogyn.servicos.dto.CadastrarOSDTO;
import com.pi.autogyn.servicos.dto.ColaboradorServicoDTO;
import com.pi.autogyn.servicos.dto.NovoModeloDTO;
import com.pi.autogyn.servicos.dto.PecaQuantidadeDTO;

public class GatewayValidacao {
	private Validador validador;

	public GatewayValidacao() {
		this.validador = new Validador();
	}

	public List<StatusValidacao> validar(CadastrarClienteDTO novoCliente) {
		List<StatusValidacao> erros = new LinkedList<>();
		
		
		if (novoCliente.isPJ()) {
			erros.add(this.validador.validaCNPJ(novoCliente.getCnpj()));
			erros.add(this.validador.validaInscricaoEstadual(novoCliente.getInscricao_estadual()));
		}
		if (!novoCliente.isPJ()) {
		erros.add(this.validador.validaCPF(novoCliente.getCpf()));	
		}
		
		erros.add(this.validador.validaEmail(novoCliente.getEmail()));
		erros.add(this.validador.validaCEP(novoCliente.getCep()));
		erros.add(this.validador.validaUF(novoCliente.getUf()));
		erros.add(this.validador.validaTelefone(novoCliente.getTelefone()));
		erros.add(this.validador.validaDDD(novoCliente.getDdd()));
		if (novoCliente.getDdd2() != null) {
			erros.add(this.validador.validaDDD(novoCliente.getDdd2()));
			erros.add(this.validador.validaTelefone(novoCliente.getTelefone2()));
		}
		removerNulos(erros);
		
		return erros;
	}

	public List<StatusValidacao> validar(CadastrarAcessorioDTO novoAcessorio) {
		List<StatusValidacao> erros = new LinkedList<>();
		return erros;
	}

	public List<StatusValidacao> validar(CadastrarOSDTO novaOS) {
		List<StatusValidacao> erros = new LinkedList<>();
		try {
			if (
				novaOS.getPlaca() == null || 
				VeiculoDao.getByPlaca(novaOS.getPlaca()) == null
			) {
				erros.add(StatusValidacao.VEICULO_NAO_ENCONTRADO);
			}
			for (ColaboradorServicoDTO colaboradorServico: novaOS.getServicos()) {
				if (colaboradorServico.getQuantidade() == null) {
					erros.add(StatusValidacao.QUANTIDADE_SERVICO_NULA);
					break;
				}
				if (colaboradorServico.getQuantidade() <= 0) {
					erros.add(StatusValidacao.QUANTIDADE_SERVICO_NEGATIVA);
				}
				if (colaboradorServico.getCpf_colaborador() == null) {
					erros.add(StatusValidacao.SEM_CPF_COLABORADOR);
					break;
				}
				ColaboradorDao.getById(colaboradorServico.getCpf_colaborador());
				if (colaboradorServico.getId_servico() == null) {
					erros.add(StatusValidacao.SEM_ID_SERVICO);
					break;
				}
			
				if (ServicoDao.getById(colaboradorServico.getId_servico()) == null) {
					erros.add(StatusValidacao.SERVICO_NAO_ENCONTRADO);
					break;
				}
			}
			
			for (PecaQuantidadeDTO pecaQuantidade: novaOS.getPecas()) {
				if (pecaQuantidade.getQuantidade() == null) {
					erros.add(StatusValidacao.QUANTIDADE_PECAS_NULA);
					break;
				}
				if (pecaQuantidade.getQuantidade() <= 0) {
					erros.add(StatusValidacao.QUANTIDADE_PECAS_NEGATIVA);
					break;
				}
				if (pecaQuantidade.getId_peca() == null) {
					erros.add(StatusValidacao.SEM_ID_PECA);
					break;
				}
				Peca peca = PecaDao.getById(pecaQuantidade.getId_peca());
				if (peca == null) {
					erros.add(StatusValidacao.PECA_NAO_ENCONTRADA);
					break;
				}
				if (peca.getQuantidadeEstoque() < pecaQuantidade.getQuantidade()) {
					erros.add(StatusValidacao.SEM_ESTOQUE);
					break;
				}
			}
			removerNulos(erros);
			return erros;
		} catch (SQLException e) {
			e.printStackTrace();
			removerNulos(erros);
			return erros;
		}
	}
	
	private static void removerNulos(List<StatusValidacao> lista) {
		// remover todos os nulos
		lista.removeIf(Objects::isNull);
	}
	
	

}
