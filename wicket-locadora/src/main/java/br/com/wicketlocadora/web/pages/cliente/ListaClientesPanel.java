package br.com.wicketlocadora.web.pages.cliente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import br.com.wicketlocadora.persistence.domain.Cliente;
import br.com.wicketlocadora.persistence.domain.QCliente;
import br.com.wicketlocadora.service.cliente.ClienteService;
import br.com.wicketlocadora.web.util.LinkPanel;
import br.com.wicketlocadora.web.util.ProviderGenerico;
import br.com.wicketlocadora.web.util.tabela.Coluna;
import br.com.wicketlocadora.web.util.tabela.Tabela;

public class ListaClientesPanel extends Panel {

    private static final long serialVersionUID = -1197209504623272195L;

    private String nomeContem;
    private String email;
    private Tabela<Cliente> tabela;

    @SpringBean
    private ClienteService service;

    public ListaClientesPanel(String id) {
	super(id);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	addFiltros();
	List<Coluna<Cliente>> colunas = new ArrayList<Coluna<Cliente>>();

	colunas.add(new Coluna<Cliente>("Nome", "nome"));
	colunas.add(new Coluna<Cliente>("E-mail", "email"));
	colunas.add(new Coluna<Cliente>("Endereço", "endereco.enderecoFormatado"));

	colunas.add(new Coluna<Cliente>("Ações", "") {

	    @Override
	    public void populateItem(Item<ICellPopulator<Cliente>> cellItem, String componentId,
		    IModel<Cliente> rowModel) {
		PageParameters parameters = new PageParameters();
		parameters.add("id", rowModel.getObject().getId());
		cellItem.add(new LinkPanel<Cliente>(componentId, "Alterar", ManterClientePage.class, parameters));
	    }
	});

	tabela = new Tabela<Cliente>("dtClientes", colunas, new ClientesProvider(), 20);
	tabela.setOutputMarkupId(true);

	add(tabela);
    }

    private void addFiltros() {
	Form formulario = new Form("formularioFiltro");
	add(formulario);
	formulario.add(new TextField<String>("nomeContem", new PropertyModel<String>(this, "nomeContem")));
	formulario.add(new TextField<String>("email", new PropertyModel<String>(this, "email")));
	formulario.add(new AjaxButton("btnFiltrar") {
	    @Override
	    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		target.add(tabela);
	    }
	});
    }

    private class ClientesProvider extends ProviderGenerico<Cliente> {

	private static final long serialVersionUID = -7569110715710900773L;

	@Override
	public Iterator<? extends Cliente> iterator(long first, long count) {
	    return service.getRepository().findAll(getFiltros()).iterator();
	}

	@Override
	public long size() {
	    return service.getRepository().count(getFiltros());
	}
    }

    private Predicate getFiltros() {
	QCliente cliente = QCliente.cliente;
	BooleanBuilder builder = new BooleanBuilder();
	if (StringUtils.isNotBlank(nomeContem)) {
	    builder.and(cliente.nome.containsIgnoreCase(nomeContem));
	}
	if (StringUtils.isNotBlank(email)) {
	    builder.and(cliente.email.equalsIgnoreCase(email));
	}

	return builder;
    }
}