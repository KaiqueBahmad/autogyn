package com.pi.autogyn.validacoes;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Validador {

	public StatusValidacao validaCPF(String CPF) {
		CPF = CPF.replace("-", "");
		CPF = CPF.replace(".", "");

		if (CPF.length() != 11) {
			return StatusValidacao.CPF_TAMANHO_ERRADO;
		}
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999")) {
			return StatusValidacao.CPF_INVALIDO;
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
			return StatusValidacao.CPF_INVALIDO;
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
			return StatusValidacao.CPF_INVALIDO;
		}

		return null;
	}

	public StatusValidacao validaCNPJ(String CNPJ) {
		CNPJ = CNPJ.replace("-", "");
		CNPJ = CNPJ.replace(".", "");
		CNPJ = CNPJ.replace("/", "");

		if (CNPJ.length() != 14) {
			return StatusValidacao.CNPJ_TAMANHO_ERRADO;
		}

		if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222")
				|| CNPJ.equals("33333333333333") || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
				|| CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || CNPJ.equals("88888888888888")
				|| CNPJ.equals("99999999999999")) {
			return StatusValidacao.CNPJ_INVALIDO;
		}
		CharacterIterator it = new StringCharacterIterator(CNPJ);
		int res1, res2, d1Input, d2Input, d1 = 0, d2 = 0, pos = 0, val = 0, peso = 5;
		int[] digitos = new int[14];
		while (it.current() != CharacterIterator.DONE) {
			digitos[pos] = Character.getNumericValue(it.current());
			it.next();
			pos++;
		}
		d1Input = digitos[12];
		d2Input = digitos[13];
		// Calculo primeiro digito
		for (int i = 0; i < digitos.length - 2; i++) {
			val += digitos[i] * peso;
			peso--;
			if (peso == 1) {
				peso = 9;
			}
		}
		res1 = val % 11;

		if (res1 < 2) {
			d1 = 0;
		} else if (res1 >= 2) {
			d1 = 11 - res1;
		}
		if (d1 != d1Input) {
			return StatusValidacao.CNPJ_INVALIDO;
		}
		// Calculo segundo digito;
		peso = 5;
		val = 0;
		for (int i = 0; i < digitos.length - 1; i++) {
			val += digitos[i] * peso;
			peso--;
			if (peso == 1) {
				peso = 9;
			}
		}
		res2 = val % 11;
		if (res2 < 2) {
			d2 = 0;
		} else if (res2 >= 2) {
			d2 = 11 - res2;
		}
		if (d2 != d2Input) {
			return StatusValidacao.CNPJ_INVALIDO;
		}
		return null;
	}

	public StatusValidacao validaEmail(String email) {
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return null;
		}
		return StatusValidacao.EMAIL_INVALIDO;
	}

	public StatusValidacao validaTelefone(Integer telefone) {
		String telefoneStr = String.valueOf(telefone);
		String regex = "^[1-9][0-9]{7,8}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(telefoneStr);

		if (matcher.matches()) {
			return null;
		}
		return StatusValidacao.TELEFONE_INVALIDO;
	}

	public StatusValidacao validaCEP(String CEP) {
		if (CEP.matches("^[0-9]{8}$")) {
			return null;
		}
		return StatusValidacao.CEP_INVALIDO;

	}

	public StatusValidacao validaUF(String UF) {
		if (UF.length() != 2) {
			return StatusValidacao.UF_INVALIDA;
		}
		return null;
	}
	
	public StatusValidacao validaDDD(Integer DDD) {
		if (DDD < 1 || DDD > 999) {
            return StatusValidacao.DDD_INVALIDO; 
        }
	    return null;
	}
	public StatusValidacao validaInscricaoEstadual(String inscricao) {
        if (inscricao.matches("^[0-9]{9,14}$")) {
            return null;
        }
        return StatusValidacao.IE_INVALIDA;
    }
	public StatusValidacao validaChassi(String chassi) {
	    if (chassi.matches("^[A-HJ-NPR-Z0-9]{17}$")) {
	        return null;
	    }
	    return StatusValidacao.NC_INVALIDO;
	}
	
	public StatusValidacao validaPlacaVeiculo(String placa) {
        if (placa.matches("^[A-Z]{3}[0-9][A-Z][0-9]{2}$") || placa.matches("^[A-Z]{3}-[0-9]{4}$")) {
            return null;
        }
        return StatusValidacao.PLACA_INVALIDA;
    }
}

