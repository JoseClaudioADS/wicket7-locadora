package br.com.wicketlocadora.web.pages.reserva;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class BotaoIncluirFilmePanel extends Panel {

    private static final long serialVersionUID = 3935770440900911378L;

    public BotaoIncluirFilmePanel(String id) {
	super(id);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	add(new AjaxButton("btnIncluir") {
	    @Override
	    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		acaoBotao(target);
	    }
	});
    }

    protected abstract void acaoBotao(AjaxRequestTarget target);

}
