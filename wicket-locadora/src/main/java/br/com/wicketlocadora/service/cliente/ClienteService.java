package br.com.wicketlocadora.service.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wicketlocadora.persistence.domain.Cliente;
import br.com.wicketlocadora.persistence.repository.ClienteRepository;
import br.com.wicketlocadora.service.IService;
import br.com.wicketlocadora.service.exception.NegocioException;

@Service
public class ClienteService implements IService<Cliente> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void manter(Cliente cliente) throws NegocioException {
	ValidadorCliente validador = new ValidadorCliente();
	validador.validarCliente(cliente);
	clienteRepository.save(cliente);
    }

    @Override
    public ClienteRepository getRepository() {
	return clienteRepository;
    }

}
