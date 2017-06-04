package br.com.wicketlocadora.service.reserva;

import org.springframework.util.CollectionUtils;

import br.com.wicketlocadora.persistence.domain.Reserva;
import br.com.wicketlocadora.service.Validador;
import br.com.wicketlocadora.service.exception.NegocioException;
import br.com.wicketlocadora.service.exception.reserva.DataInicialMaiorQueFinalException;
import br.com.wicketlocadora.service.exception.reserva.NenhumFilmeSelecionadoException;

public class ValidadorReserva extends Validador {

    public void validarReserva(Reserva reserva) throws NegocioException {

	validarNuloOuVazio(reserva, "Reserva");
	validar();
	validarNuloOuVazio(reserva.getDataInicio(), "Data Início");
	validarNuloOuVazio(reserva.getDataFinal(), "Data Final");
	validar();
	validarNuloOuVazio(reserva.getCliente(), "Cliente");
	validar();
	validarDatas(reserva);
	validarFilmes(reserva);

    }

    private void validarDatas(Reserva reserva) throws DataInicialMaiorQueFinalException {

	if (reserva.getDataInicio().isAfter(reserva.getDataFinal())) {
	    throw new DataInicialMaiorQueFinalException();
	}

    }

    private void validarFilmes(Reserva reserva) throws NenhumFilmeSelecionadoException {

	if (CollectionUtils.isEmpty(reserva.getFilmes())) {
	    throw new NenhumFilmeSelecionadoException();
	}

    }

}
