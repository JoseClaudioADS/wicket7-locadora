package br.com.wicketlocadora.service.reserva;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wicketlocadora.persistence.domain.Reserva;
import br.com.wicketlocadora.persistence.repository.ClienteRepository;
import br.com.wicketlocadora.persistence.repository.ReservaRepository;
import br.com.wicketlocadora.service.IService;
import br.com.wicketlocadora.service.exception.NegocioException;
import br.com.wicketlocadora.web.util.dto.ReservaDTO;

@Service
public class ReservaService implements IService<Reserva> {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void reservar(ReservaDTO reservaDTO) throws NegocioException {
	Reserva reserva = montarReserva(reservaDTO);
	ValidadorReserva validador = new ValidadorReserva();
	validador.validarReserva(reserva);
	getRepository().save(reserva);
    }

    // Uma excelente alternativa é utilizar a biblioteca ModelMapper ou
    // semelhantes (Ex: Orika)
    @Transactional(readOnly = true)
    private Reserva montarReserva(ReservaDTO reservaDTO) {
	Reserva reserva = new Reserva();
	reserva.setDataInicio(reservaDTO.getDataInicio());
	reserva.setDataFinal(reservaDTO.getDataFinal());
	if (StringUtils.isNotBlank(reservaDTO.getCliente())) {
	    reserva.setCliente(clienteRepository.findByNome(reservaDTO.getCliente()));
	}
	return reserva;
    }

    @Override
    public ReservaRepository getRepository() {
	return reservaRepository;
    }

}
