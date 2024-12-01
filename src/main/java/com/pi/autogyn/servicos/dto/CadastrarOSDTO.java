package com.pi.autogyn.servicos.dto;

import java.util.List;

public class CadastrarOSDTO {
	private Long id_veiculo;
	private Double valor_orcamento;
	private List<ColaboradorServicoDTO> servicos;
	private List<PecaQuantidadeDTO> pecas;
}
