package br.com.wicketlocadora.service.exception.filme;

import java.util.Arrays;

import br.com.wicketlocadora.service.exception.NegocioException;

public class CategoriaNaoInformadaException extends NegocioException {

    private static final long serialVersionUID = -7164811541731372929L;

    public CategoriaNaoInformadaException() {
	super(Arrays.asList("Categoria não informada exception."));
    }

}
