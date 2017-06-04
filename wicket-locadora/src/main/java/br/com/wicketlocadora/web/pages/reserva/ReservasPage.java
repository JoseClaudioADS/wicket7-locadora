package br.com.wicketlocadora.web.pages.reserva;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import br.com.wicketlocadora.persistence.domain.Reserva;
import br.com.wicketlocadora.web.pages.template.RaizPage;

public class ReservasPage extends RaizPage {

    private static final long serialVersionUID = 7207165803101379985L;

    @Override
    protected void onInitialize() {
	super.onInitialize();
	add(new BookmarkablePageLink<Reserva>("linkReservar", ReservarPage.class));
    }

}
