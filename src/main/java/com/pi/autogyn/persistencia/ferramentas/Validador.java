package com.pi.autogyn.persistencia.ferramentas;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class Validador {
	public static String validaCPF(String CPF) {
		CPF = CPF.replace("-", "");
		CPF = CPF.replace(".", "");
		System.out.println(CPF);
		if (CPF.length() != 11) {
			return "CPF não contem 11 digitos";
		}
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999")) {
			return "CPF inválido";
		}
		CharacterIterator it = new StringCharacterIterator(CPF);
		int res1, res2, d1Input, d2Input, d1 = 0, d2 = 0;
		int peso = 10;
		int[] digitos = new int[11];
		int pos = 0;
		int val = 0;
		while (it.current() != CharacterIterator.DONE) {
			digitos[pos] = Character.getNumericValue(it.current());	
			
			it.next();
			pos++;
		}
		d1Input = digitos[9];
		d2Input = digitos[10];
		// Calculo primeiro digito
		for (int i = 0; i < digitos.length - 2; i++) {
			val += digitos[i] * peso;
			peso--;
		}
		res1 = val % 11;
		if (res1 < 2) {
			d1 = 0;
		} else if (res1 >= 2) {
			d1 = 11 - res1;
		}
		if (d1 != d1Input) {
			return "CPF Inválido";
		}
		// Calculo segundo digito;
		peso = 11;
		val = 0;
		for (int i = 0; i < digitos.length - 1; i++) {
			val += digitos[i] * peso;
			peso--;
		}
		res2 = val % 11;
		if (res2 < 2) {
			d2 = 0;
		} else if (res2 >= 2) {
			d2 = 11 - res2;
		}
		if (d2 != d2Input) {
			return "CPF Inválido";
		}

		return "ok";
	}

	public static boolean validaCNPJ(String CNPJ) {
		return false;
	}

	public static boolean validaEmail(String Email) {
		return false;
	}

	public static boolean validaTelefone(Integer Telefone) {
		return false;
	}

	public static boolean validaCEP(String CEP) {
		return false;
	}

	public static boolean validaUF(String UF) {
		return false;
	}

}
