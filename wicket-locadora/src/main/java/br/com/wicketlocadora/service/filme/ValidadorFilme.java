package br.com.wicketlocadora.service.filme;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.wicket.markup.html.form.upload.FileUpload;

import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.service.Validador;
import br.com.wicketlocadora.service.exception.NegocioException;
import br.com.wicketlocadora.service.exception.filme.CapaNaoInformadaException;
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
	if (CollectionUtils.isEmpty(filme.getCategorias())) {
	    throw new CategoriaNaoInformadaException();
	}
    }

    public void validarCapa(FileUpload capa) throws CapaNaoInformadaException {
	if (capa == null) {
	    throw new CapaNaoInformadaException();
	}
    }

}
