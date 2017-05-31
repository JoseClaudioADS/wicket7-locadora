package br.com.wicketlocadora.persistence.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.wicketlocadora.persistence.domain.enumeracoes.UF;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
public class Endereco implements Serializable {

    private static final long serialVersionUID = -641051553510456855L;

    private String logradouro;
    private String cidade;

    @Enumerated(EnumType.STRING)
    private UF uf;

}
