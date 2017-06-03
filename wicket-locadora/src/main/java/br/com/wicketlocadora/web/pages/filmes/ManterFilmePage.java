package br.com.wicketlocadora.web.pages.filmes;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.string.StringValue;

import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.persistence.repository.CategoriaRepository;
import br.com.wicketlocadora.service.exception.NegocioException;
import br.com.wicketlocadora.service.exception.filme.FilmeService;
import br.com.wicketlocadora.web.pages.template.RaizPage;

public class ManterFilmePage extends RaizPage {

    private static final long serialVersionUID = 8059638603209899024L;

    private Filme filme;
    private CompoundPropertyModel<Filme> filmeModel;

    @SpringBean
    private FilmeService filmeService;

    @SpringBean
    private CategoriaRepository categoriaRepository;

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
	formulario.setMultiPart(true);// sinalizando ao form para ser
	// MultiPart
	// e permitir upload de arquivos
	formulario.setMaxSize(Bytes.kilobytes(100));
	formulario.setFileMaxSize(Bytes.kilobytes(90));
	add(formulario);
	DadosFilmePanel dadosFilmePanel = new DadosFilmePanel("painelDadosFilme");
	formulario.add(dadosFilmePanel);
	formulario.add(new Button("btnSalvar") {
	    @Override
	    public void onSubmit() {
		try {
		    filmeService.manter(filme, dadosFilmePanel.getCapa().getFileUpload());
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
