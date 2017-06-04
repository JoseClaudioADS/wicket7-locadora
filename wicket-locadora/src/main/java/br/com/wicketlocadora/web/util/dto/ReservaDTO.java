package br.com.wicketlocadora.web.util.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaDTO implements Serializable {

    private static final long serialVersionUID = 6799086893073377171L;

    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private String cliente;
    private List<Long> idsFilmes;

}
