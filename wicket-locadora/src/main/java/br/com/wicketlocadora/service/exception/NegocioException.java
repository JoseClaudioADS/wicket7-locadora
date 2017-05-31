package br.com.wicketlocadora.service.exception;

import java.util.List;

import lombok.Getter;

@Getter
public class NegocioException extends Exception {

    private static final long serialVersionUID = -1833506504893119792L;

    private List<String> erros;

    public NegocioException(List<String> erros) {
	this.erros = erros;
    }

}
