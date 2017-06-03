package br.com.wicketlocadora.web.pages.filmes;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.service.exception.NegocioException;
import br.com.wicketlocadora.service.exception.filme.FilmeService;
import br.com.wicketlocadora.web.pages.template.RaizPage;

public class ManterFilmePage extends RaizPage {

    private Filme filme;
    private CompoundPropertyModel<Filme> filmeModel;

    @SpringBean
    private FilmeService filmeService;

    public ManterFilmePage(PageParameters parameters) {
	StringValue id = parameters.get("id");
	if (id.isNull()) {
	    this.filme = new Filme();
	} else {
	    this.filme = filmeService.getRepository().findOne(id.toLongObject());
	}
	filmeModel = new CompoundPropertyModel<Filme>(filme);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	boolean cadastrando = filme.getId() == null;
	add(new Label("titulo", cadastrando ? "Novo" : "Alterar"));
	Form<Filme> formulario = new Form<Filme>("formulario", filmeModel);
	add(formulario);
	formulario.add(new DadosFilmePanel("painelDadosFilme", filmeModel));
	formulario.add(new Button("btnSalvar") {
	    @Override
	    public void onSubmit() {
		try {
		    filmeService.manter(filme);
		    getSession()
			    .success(String.format("Filme %s com sucesso.", cadastrando ? "cadastrado" : "alterado"));
		    setResponsePage(FilmesPage.class);
		} catch (NegocioException e) {
		    lancarErros(e.getErros());
		}
	    }
	});
    }
}
