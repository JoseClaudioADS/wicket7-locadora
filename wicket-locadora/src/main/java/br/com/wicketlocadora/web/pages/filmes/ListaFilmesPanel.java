package br.com.wicketlocadora.web.pages.filmes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.service.exception.filme.FilmeService;
import br.com.wicketlocadora.web.util.ProviderGenerico;
import br.com.wicketlocadora.web.util.tabela.Coluna;
import br.com.wicketlocadora.web.util.tabela.Tabela;

public class ListaFilmesPanel extends Panel {

    private static final long serialVersionUID = -1197209504623272195L;

    @SpringBean
    private FilmeService service;

    public ListaFilmesPanel(String id) {
	super(id);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	List<Coluna<Filme>> colunas = new ArrayList<Coluna<Filme>>();

	colunas.add(new Coluna<Filme>("Título", "titulo"));
	colunas.add(new Coluna<Filme>("Descrição", "descricao"));

	Tabela<Filme> tabela = new Tabela<Filme>("dtFilmes", colunas, new FilmesProvider(), 20);

	add(tabela);
    }

    private class FilmesProvider extends ProviderGenerico<Filme> {

	private static final long serialVersionUID = -1742576156273957772L;

	@Override
	public Iterator<? extends Filme> iterator(long first, long count) {
	    return service.getRepository().findAll().iterator();
	}

	@Override
	public long size() {
	    return service.getRepository().count();
	}
    }
}