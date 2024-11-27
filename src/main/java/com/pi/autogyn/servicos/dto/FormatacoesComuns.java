package com.pi.autogyn.servicos.dto;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatacoesComuns {
	public static String doubleToBRL(Double value) {
	    if (value == null) {
	        return "R$ 0,00";
	    }
	    
	    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	    return formatter.format(value);
	}
}
