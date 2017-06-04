package br.com.wicketlocadora.service.exception.reserva;

import java.util.Arrays;

import br.com.wicketlocadora.service.exception.NegocioException;

public class DataInicialMaiorQueFinalException extends NegocioException {

    private static final long serialVersionUID = -8376920325538924406L;

    public DataInicialMaiorQueFinalException() {
	super(Arrays.asList("Data inicial maior que a data final."));
    }

}
