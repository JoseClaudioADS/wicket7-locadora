package br.com.wicketlocadora.web.pages.filmes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import br.com.wicketlocadora.persistence.domain.Categoria;
import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.persistence.domain.QFilme;
import br.com.wicketlocadora.persistence.repository.CategoriaRepository;
import br.com.wicketlocadora.service.exception.filme.FilmeService;
import br.com.wicketlocadora.web.util.LinkImagemPanel;
import br.com.wicketlocadora.web.util.ProviderGenerico;
import br.com.wicketlocadora.web.util.tabela.Coluna;
import br.com.wicketlocadora.web.util.tabela.Tabela;

public class ListaFilmesPanel extends Panel {

    private static final long serialVersionUID = -1197209504623272195L;

    private String tituloContem;
    private Categoria categoria;

    private Tabela<Filme> tabela;

    @SpringBean
    private FilmeService service;

    @SpringBean
    private CategoriaRepository categoriaRepository;

    public ListaFilmesPanel(String id) {
	super(id);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	addFiltros();
	List<Coluna<Filme>> colunas = new ArrayList<Coluna<Filme>>();

	colunas.add(new Coluna<Filme>("Capa", "") {

	    @Override
	    public void populateItem(Item<ICellPopulator<Filme>> cellItem, String componentId, IModel<Filme> rowModel) {
		PageParameters parameters = new PageParameters();
		parameters.add("id", rowModel.getObject().getId());
		cellItem.add(new LinkImagemPanel<Filme>(componentId,
			FilmeService.NOME_PASTA_CAPAS + "\\" + rowModel.getObject().getCapa(), "100", "50",
			ManterFilmePage.class, parameters));
	    }
	});

	colunas.add(new Coluna<Filme>("Título", "titulo"));
	colunas.add(new Coluna<Filme>("Descrição", "descricao"));
	addColunasExtras(colunas);

	tabela = new Tabela<Filme>("dtFilmes", colunas, new FilmesProvider(), 20);
	tabela.setOutputMarkupId(true);

	add(tabela);
    }

    public void addColunasExtras(List<Coluna<Filme>> colunas) {
	// não faz nada aqui
    }

    private void addFiltros() {
	Form formulario = new Form("formularioFiltro");
	add(formulario);
	formulario.add(new TextField<String>("tituloContem", new PropertyModel<String>(this, "tituloContem")));
	DropDownChoice<Categoria> categoriasFiltro = new DropDownChoice<Categoria>("categoriasFiltro",
		new PropertyModel<Categoria>(this, "categoria"), categoriaRepository.todasAsCategorias(),
		new ChoiceRenderer<Categoria>("nome"));
	formulario.add(categoriasFiltro);
	formulario.add(new AjaxButton("btnFiltrar") {
	    @Override
	    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		target.add(tabela);
	    }
	});
    }

    @Transactional(readOnly = true)
    private class FilmesProvider extends ProviderGenerico<Filme> {

	private static final long serialVersionUID = -1742576156273957772L;

	@Override
	public Iterator<? extends Filme> iterator(long first, long count) {
	    return service.getRepository().findAll(getFiltros()).iterator();
	}

	@Override
	public long size() {
	    return service.getRepository().count(getFiltros());
	}
    }

    @Transactional(readOnly = true)
    private Predicate getFiltros() {
	BooleanBuilder builder = new BooleanBuilder();
	QFilme filme = QFilme.filme;

	if (StringUtils.isNotBlank(tituloContem)) {
	    builder.and(filme.titulo.containsIgnoreCase(tituloContem));
	}

	if (categoria != null) {
	    builder.and(filme.categorias.any().id.eq(categoria.getId()));
	}

	return builder;
    }
}