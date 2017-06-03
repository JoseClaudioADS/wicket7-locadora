package br.com.wicketlocadora.web.pages.filmes;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.web.pages.template.RaizPage;

public class FilmesPage extends RaizPage {

    private static final long serialVersionUID = 468799002269726074L;

    @Override
    protected void onInitialize() {
	super.onInitialize();
	add(new BookmarkablePageLink<Filme>("linkNovoFilme", ManterFilmePage.class));
	add(new ListaFilmesPanel("listaFilmePanel"));
    }

}