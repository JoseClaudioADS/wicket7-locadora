package br.com.wicketlocadora.service.filme;

import java.util.Set;

import br.com.wicketlocadora.persistence.domain.Categoria;
import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.service.Validador;
import br.com.wicketlocadora.service.exception.NegocioException;
import br.com.wicketlocadora.service.exception.filme.CategoriaNaoInformadaException;

public class ValidadorFilme extends Validador {

    public void validarFilme(Filme filme) throws NegocioException {
	validarNuloOuVazio(filme, "Filme");
	validar();
	validarNuloOuVazio(filme.getTitulo(), "Título");
	validarNuloOuVazio(filme.getDescricao(), "Descrição");
	validar();
	validarCategoria(filme);
    }

    private void validarCategoria(Filme filme) throws CategoriaNaoInformadaException {
	Set<Categoria> categorias = filme.getCategorias();
	if (categorias == null || categorias.isEmpty()) {
	    throw new CategoriaNaoInformadaException();
	}
    }

}
