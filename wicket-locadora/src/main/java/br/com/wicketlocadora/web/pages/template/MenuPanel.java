package br.com.wicketlocadora.web.pages.template;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.wicketlocadora.persistence.domain.Cliente;
import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.persistence.domain.Reserva;
import br.com.wicketlocadora.web.pages.cliente.ClientesPage;
import br.com.wicketlocadora.web.pages.filmes.FilmesPage;
import br.com.wicketlocadora.web.pages.reserva.ReservasPage;

public class MenuPanel extends Panel {

    private static final long serialVersionUID = -5406325311227883941L;

    public MenuPanel(String id) {
	super(id);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	add(new BookmarkablePageLink<Cliente>("linkClientes", ClientesPage.class));
	add(new BookmarkablePageLink<Filme>("linkFilmes", FilmesPage.class));
	add(new BookmarkablePageLink<Reserva>("linkReservas", ReservasPage.class));
    }
}
