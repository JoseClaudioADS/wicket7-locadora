package br.com.wicketlocadora.web.pages.cliente;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import br.com.wicketlocadora.persistence.domain.Cliente;
import br.com.wicketlocadora.web.pages.template.RaizPage;

public class ClientesPage extends RaizPage {

    private static final long serialVersionUID = 4626430418939650212L;

    @Override
    protected void onInitialize() {
	super.onInitialize();
	add(new BookmarkablePageLink<Cliente>("linkNovoCliente", ManterClientePage.class));
    }

}