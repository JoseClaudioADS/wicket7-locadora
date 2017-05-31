package br.com.wicketlocadora.web.pages.template;

import org.apache.wicket.markup.html.WebPage;

public class RaizPage extends WebPage {

    private static final long serialVersionUID = -6836630541254981348L;

    @Override
    protected void onInitialize() {
	super.onInitialize();
	add(new MenuPanel("painelMenu"));
    }

}
