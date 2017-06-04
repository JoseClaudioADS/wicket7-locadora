package br.com.wicketlocadora.service.exception.reserva;

import java.util.Arrays;

import br.com.wicketlocadora.service.exception.NegocioException;

public class NenhumFilmeSelecionadoException extends NegocioException {

    private static final long serialVersionUID = -1124537581559786092L;

    public NenhumFilmeSelecionadoException() {
	super(Arrays.asList("Nenhum filme selecionado."));
    }

}
