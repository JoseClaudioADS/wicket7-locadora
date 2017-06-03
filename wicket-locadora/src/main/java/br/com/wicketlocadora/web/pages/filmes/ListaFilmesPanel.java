package br.com.wicketlocadora.web.pages.filmes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.service.exception.filme.FilmeService;
import br.com.wicketlocadora.web.util.LinkImagemPanel;
import br.com.wicketlocadora.web.util.ProviderGenerico;
import br.com.wicketlocadora.web.util.tabela.Coluna;
import br.com.wicketlocadora.web.util.tabela.Tabela;

public class ListaFilmesPanel extends Panel {

    private static final long serialVersionUID = -1197209504623272195L;

    @SpringBean
    private FilmeService service;

    public ListaFilmesPanel(String id) {
	super(id);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
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

	Tabela<Filme> tabela = new Tabela<Filme>("dtFilmes", colunas, new FilmesProvider(), 20);

	add(tabela);
    }

    private class FilmesProvider extends ProviderGenerico<Filme> {

	private static final long serialVersionUID = -1742576156273957772L;

	@Override
	public Iterator<? extends Filme> iterator(long first, long count) {
	    return service.getRepository().findAll().iterator();
	}

	@Override
	public long size() {
	    return service.getRepository().count();
	}
    }
}