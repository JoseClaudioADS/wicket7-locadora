package br.com.wicketlocadora.web.pages.template;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.wicketlocadora.persistence.domain.Cliente;
import br.com.wicketlocadora.web.pages.cliente.ClientesPage;

public class MenuPanel extends Panel {

    private static final long serialVersionUID = -8758019266912719718L;

    public MenuPanel(String id) {
	super(id);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	add(new BookmarkablePageLink<Cliente>("linkClientes", ClientesPage.class));
    }
}
