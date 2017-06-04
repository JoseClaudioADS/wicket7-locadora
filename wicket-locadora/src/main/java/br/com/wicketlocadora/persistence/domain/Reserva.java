package br.com.wicketlocadora.persistence.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "res_reserva")
@AttributeOverride(column = @Column(name = "res_id"), name = "id")
@Getter
@Setter
@ToString
public class Reserva extends Entidade {

    private static final long serialVersionUID = 5208450299856837822L;

    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "res_id"), inverseJoinColumns = @JoinColumn(name = "flm_id"))
    private List<Filme> filmes;

    private LocalDate dataInicio;
    private LocalDate dataFinal;

}
