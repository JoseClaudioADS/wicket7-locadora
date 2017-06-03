package br.com.wicketlocadora.service.exception.filme;

import java.util.Arrays;

import br.com.wicketlocadora.service.exception.NegocioException;

public class CapaNaoInformadaException extends NegocioException {

    private static final long serialVersionUID = -7769109324323157479L;

    public CapaNaoInformadaException() {
	super(Arrays.asList("Capa não informada."));
    }

}
