package br.com.wicketlocadora.persistence.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.wicketlocadora.persistence.domain.enumeracoes.UF;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Endereco {

    private String logradouro;
    private String cidade;

    @Enumerated(EnumType.STRING)
    private UF uf;

}
