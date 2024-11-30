package com.pi.autogyn.servicos;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.pi.autogyn.persistencia.dao.AcessorioDao;
import com.pi.autogyn.persistencia.dao.ClienteDao;
import com.pi.autogyn.persistencia.dao.MarcaDao;
import com.pi.autogyn.persistencia.dao.ModeloDao;
import com.pi.autogyn.persistencia.dao.VeiculoDao;
import com.pi.autogyn.persistencia.entidades.Acessorio;
import com.pi.autogyn.persistencia.entidades.Marca;
import com.pi.autogyn.persistencia.entidades.Modelo;
import com.pi.autogyn.persistencia.entidades.Veiculo;
import com.pi.autogyn.servicos.dto.AtualizarVeiculoDTO;
import com.pi.autogyn.servicos.dto.CadastrarVeiculoDTO;
import com.pi.autogyn.servicos.dto.MarcaListaCadastroDTO;
import com.pi.autogyn.servicos.dto.MinimalAcessorioDTO;
import com.pi.autogyn.servicos.dto.MinimalMarcaDTO;
import com.pi.autogyn.servicos.dto.ModeloDTO;
import com.pi.autogyn.servicos.dto.NovoModeloDTO;
import com.pi.autogyn.servicos.dto.VeiculoListaCadastradosDto;
import com.pi.autogyn.validacoes.GatewayValidacao;

public class VeiculoService {
	public static List<VeiculoListaCadastradosDto> listarVeiculosCadastrados() throws SQLException {
		List<VeiculoListaCadastradosDto> lista = new LinkedList<>();
		for (Veiculo veiculo: VeiculoDao.getAll()) {
			lista.add(new VeiculoListaCadastradosDto(veiculo));
		}
		return lista;
	}
	
	public static List<MarcaListaCadastroDTO> listarMarcasCadastradas() throws SQLException {
		List<MarcaListaCadastroDTO> lista = new LinkedList<>();
		for (Marca marca: MarcaDao.getAll()) {
			lista.add(new MarcaListaCadastroDTO(marca));
		}
		return lista;
	}
	
	public static List<MinimalMarcaDTO> listarMinimalMarcas() throws SQLException {
		List<MinimalMarcaDTO> lista = new LinkedList<>();
		for (Marca marca: MarcaDao.getAll()) {
			lista.add(new MinimalMarcaDTO(marca));
		}
		return lista;
	}
	
	
	public static boolean insertMarca(String nomeModelo) throws SQLException {
		return MarcaDao.insert(nomeModelo);
	}
	
	public static boolean insertModelo(NovoModeloDTO novoModelo) throws SQLException {
		return ModeloDao.insert(Long.parseLong(novoModelo.getIdMarca()), novoModelo.getNomeModelo());
	}

	public static List<MinimalAcessorioDTO> listarMinimalAcessorios() throws SQLException {
		List<MinimalAcessorioDTO> lista = new LinkedList<>();
		for (Acessorio acessorio: AcessorioDao.getAll()) {
			lista.add(new MinimalAcessorioDTO(acessorio));
		}
		return lista;
	}

	public static List<ModeloDTO> listarModelosPorMarca(Long idMarca) throws SQLException {
		Marca marca = MarcaDao.getById(idMarca);
		if (marca == null) {
			return null;
		}
		List<ModeloDTO> lista = new LinkedList<>();
		for (Modelo modelo: marca.getModelos()) {
			lista.add(new ModeloDTO(modelo));
		}
		return lista;
	}

	public static String atualizarVeiculo(String placa, AtualizarVeiculoDTO atualizarVeiculo) throws SQLException {
		
		if (atualizarVeiculo.getDocumentoNovoDono() != null) {
			if (atualizarVeiculo.getDocumentoNovoDono().length() <= 11) {
				ClienteDao.getByCNPJ(placa);
			} else {				
				ClienteDao.getByCPF(atualizarVeiculo.getDocumentoNovoDono());
			}
		}
		
		if (atualizarVeiculo.getQuilometragem() != null) {
			if (atualizarVeiculo.getQuilometragem() < 0) {
				return "Quilometragem deve ser maior ou igual a zero.";
			}
			VeiculoDao.atualizarQuilometragem(placa, atualizarVeiculo.getQuilometragem());
		}
		return null;
	}

	public static String insertVeiculo(CadastrarVeiculoDTO novoVeiculo) throws SQLException {
		Set<Long> acessoriosExistentes = new HashSet<>();
		for (Acessorio acessorio: AcessorioDao.getAll()) {
			acessoriosExistentes.add(acessorio.getId());
		}
		for (Long acessorio: novoVeiculo.getAcessorios()) {
			if (acessorio == null ) {
				return "Acessorio nulo recebido na requisição";
			}
			if (!acessoriosExistentes.contains(acessorio)) {
				return "Acessorio não encotrado. id="+acessorio;
			}
		}
		String placa;
		try {
			placa = VeiculoDao.insert(novoVeiculo);
		} catch (IllegalStateException e) {
			return e.getMessage();
		}
		for (Long acessorio: new HashSet<Long>(novoVeiculo.getAcessorios())) {
			try {
				VeiculoDao.addAcessorio(placa, acessorio);
			} catch (IllegalStateException e) {
				System.out.println("ERRO ERRO "+e.getMessage());
				continue;
			} catch (SQLException sqlEx) {
				System.out.println("ERRO ERRO ERRO "+sqlEx.getMessage());
				continue;
			}
		}
		return null;
	}
	
}
