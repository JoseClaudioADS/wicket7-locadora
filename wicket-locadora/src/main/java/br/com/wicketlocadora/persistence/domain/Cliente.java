package br.com.wicketlocadora.persistence.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cli_cliente")
@AttributeOverride(column = @Column(name = "cli_id"), name = "id")
@Getter
@Setter
public class Cliente extends Entidade {

    private static final long serialVersionUID = 166002450591384507L;

    private String nome;
    private String email;

    @Embedded
    private Endereco endereco;

}
