package com.pi.autogyn.servicos.dto;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.pi.autogyn.persistencia.entidades.OS;

public class OrdemServicoListaDTO {
	public Long id;
	public String veiculo;
	public String data;
	public String valor;
	public String status;
	public String cliente;
	
	public OrdemServicoListaDTO(OS os) {
		this.id = os.getId();
		this.veiculo = String.format(
			"%s (%s) %d",
			os.getVeiculo().getModelo().getNome(),
			os.getVeiculo().getModelo().getMarca().getNome(),
			os.getVeiculo().getAnoModelo()
		);
		Date data = os.getData();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(data);
	    this.data = String.format("%02d/%02d/%d", 
	        cal.get(Calendar.DAY_OF_MONTH),
	        cal.get(Calendar.MONTH) + 1,
	        cal.get(Calendar.YEAR)
	    );
	    NumberFormat formatador = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	    this.valor = formatador.format(os.getValor_total());
	    this.status = os.getEtapa().valor();
	    if (os.getCliente().getPessoaFisica().isPresent()) {
	    	this.cliente = String.format(
				"[%s] %s",
				os.getCliente().getPessoaFisica().get().getCpf(),
				os.getCliente().getNome()
			);
	    } else if (os.getCliente().getPessoaJuridica().isPresent()) {
	    	this.cliente = String.format(
				"[%s] %s",
				os.getCliente().getPessoaJuridica().get().getCnpj(),
				os.getCliente().getNome()
			);
	    } else if (os.getCliente() != null) {
	    	this.cliente = os.getCliente().getNome();
	    } else {
	    	this.cliente = "[Cliente não encontrado]";
	    }
	}
	
}
