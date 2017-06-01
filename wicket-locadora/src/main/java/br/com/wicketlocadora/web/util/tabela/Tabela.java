package br.com.wicketlocadora.web.util.tabela;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import br.com.wicketlocadora.persistence.domain.Entidade;

public class Tabela<T extends Entidade> extends DataTable<T, String> {

    public Tabela(String id, List<Coluna<T>> colunas, IDataProvider<T> dataProvider, long linhasPorPagina) {
	super(id, colunas, dataProvider, linhasPorPagina);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	addBottomToolbar(new NavigationToolbar(this));
	addTopToolbar(new HeadersToolbar<String>(this, null));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
	super.onComponentTag(tag);
	tag.put("class", "table");
    }

}