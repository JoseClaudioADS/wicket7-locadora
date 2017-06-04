package br.com.wicketlocadora.service.reserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wicketlocadora.persistence.domain.Reserva;
import br.com.wicketlocadora.persistence.repository.ReservaRepository;
import br.com.wicketlocadora.service.IService;
import br.com.wicketlocadora.service.exception.NegocioException;

@Service
public class ReservaService implements IService<Reserva> {

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional
    public void reservar(Reserva reserva) throws NegocioException {
	System.out.println(reserva);
    }

    @Override
    public ReservaRepository getRepository() {
	return reservaRepository;
    }

}
