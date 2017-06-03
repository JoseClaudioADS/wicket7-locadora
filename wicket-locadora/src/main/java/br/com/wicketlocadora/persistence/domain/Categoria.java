package br.com.wicketlocadora.persistence.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cat_categoria")
@AttributeOverride(column = @Column(name = "cat_id"), name = "id")
@Getter
@Setter
@ToString
public class Categoria extends Entidade {

    private static final long serialVersionUID = 5591041083090550189L;

    private String nome;

}
