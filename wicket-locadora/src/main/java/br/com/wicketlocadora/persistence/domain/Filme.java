package br.com.wicketlocadora.persistence.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "flm_filme")
@AttributeOverride(column = @Column(name = "flm_id"), name = "id")
@Getter
@Setter
@ToString
public class Filme extends Entidade {

    private static final long serialVersionUID = -6691820574038561035L;

    private String titulo;
    private String descricao;
    private String capa;

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "flm_id"), inverseJoinColumns = @JoinColumn(name = "cat_id"))
    private Set<Categoria> categorias;

}
