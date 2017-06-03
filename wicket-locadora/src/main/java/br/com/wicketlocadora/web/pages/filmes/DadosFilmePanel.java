package br.com.wicketlocadora.web.pages.filmes;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import br.com.wicketlocadora.persistence.domain.Filme;

public class DadosFilmePanel extends Panel {

    private static final long serialVersionUID = -3618001620594800540L;

    private CompoundPropertyModel<Filme> filmeModel;

    public DadosFilmePanel(String id, CompoundPropertyModel<Filme> filmeModel) {
	super(id);
	this.filmeModel = filmeModel;
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	add(new TextField<String>("titulo"));
	add(new TextField<String>("descricao"));

    }

}