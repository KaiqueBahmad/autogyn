package com.pi.autogyn.servicos.dto;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.pi.autogyn.persistencia.entidades.OS;

public class OrdemServicoListaDTO {
	public String veiculo;
	public String data;
	public String valor;
	public String status;
	
	public OrdemServicoListaDTO(OS os) {
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
	}
	
}
