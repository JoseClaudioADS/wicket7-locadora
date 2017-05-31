package br.com.wicketlocadora.persistence.domain.enumeracoes;

import lombok.Getter;

@Getter
public enum UF {

    PE("Pernambuco"), //
    SP("São Paulo");

    private String nome;

    private UF(String nome) {
	this.nome = nome;
    }

}
