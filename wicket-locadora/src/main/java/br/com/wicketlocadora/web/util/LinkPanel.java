package br.com.wicketlocadora.web.util;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.wicketlocadora.persistence.domain.Entidade;

public class LinkPanel<T extends Entidade> extends Panel {

    private static final long serialVersionUID = -6255553288891520397L;

    private String texto;
    private Class pagina;
    private PageParameters parameters;

    public LinkPanel(String id, String texto, Class pagina, PageParameters parameters) {
	super(id);
	this.texto = texto;
	this.pagina = pagina;
	this.parameters = parameters;
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	BookmarkablePageLink<T> link = new BookmarkablePageLink<T>("link", this.pagina, this.parameters);
	link.add(new Label("label", this.texto));
	add(link);
    }
}
