package br.com.wicketlocadora.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.wicketlocadora.service.exception.NegocioException;

public abstract class Validador {

    private List<String> erros;

    public Validador() {
	this.erros = new ArrayList<String>();
    }

    public Validador validarNuloOuVazio(Object valor, String nomeCampo) {
	boolean erro = valor instanceof String ? StringUtils.isBlank((String) valor) : valor == null;
	if (erro) {
	    addErro(String.format("O campo %s é de preenchimento obrigatório.", nomeCampo));
	}
	return this;
    }

    public void addErro(String mgs) {
	erros.add(mgs);
    }

    public void validar() throws NegocioException {
	if (!erros.isEmpty()) {
	    throw new NegocioException(erros);
	}
    }

}
