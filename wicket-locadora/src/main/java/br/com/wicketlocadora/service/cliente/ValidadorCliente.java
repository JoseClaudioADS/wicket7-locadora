package br.com.wicketlocadora.service.cliente;

import br.com.wicketlocadora.persistence.domain.Cliente;
import br.com.wicketlocadora.service.Validador;
import br.com.wicketlocadora.service.exception.NegocioException;

public class ValidadorCliente extends Validador {

    public void validarCliente(Cliente cliente) throws NegocioException {
	validarNuloOuVazio(cliente, "Cliente");
	validar();
	validarNuloOuVazio(cliente.getNome(), "Nome");
	validarNuloOuVazio(cliente.getEmail(), "E-mail");
	validar();
	validarNuloOuVazio(cliente.getEndereco(), "Endereço");
	validar();
	validarNuloOuVazio(cliente.getEndereco().getLogradouro(), "Logradouro");
	validarNuloOuVazio(cliente.getEndereco().getCidade(), "Cidade");
	validarNuloOuVazio(cliente.getEndereco().getUf(), "UF");
	validar();
    }

}