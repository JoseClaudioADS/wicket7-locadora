package br.com.wicketlocadora.web.util.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO criado para facilitar a passagem de valores para o service. <br>
 * Como o wicket trabalha com objetos transientes, guardo aqui apenas
 * identificadores <br>
 * e depois carrego os objetos
 * 
 * @author Junior
 *
 */
@Getter
@Setter
public class ReservaDTO implements Serializable {

    private static final long serialVersionUID = 6799086893073377171L;

    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private String cliente;
    private Set<Long> idsFilmes;

}
