package br.com.wicketlocadora.web.pages.cliente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.wicketlocadora.persistence.domain.Cliente;
import br.com.wicketlocadora.persistence.repository.ClienteRepository;
import br.com.wicketlocadora.web.util.ProviderGenerico;
import br.com.wicketlocadora.web.util.tabela.Coluna;
import br.com.wicketlocadora.web.util.tabela.Tabela;

public class ListaClientesPanel extends Panel {

    private static final long serialVersionUID = -1197209504623272195L;

    @SpringBean
    private ClienteRepository repository;

    public ListaClientesPanel(String id) {
	super(id);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	List<Coluna<Cliente>> colunas = new ArrayList<Coluna<Cliente>>();

	colunas.add(new Coluna<Cliente>("Nome", "nome"));
	colunas.add(new Coluna<Cliente>("E-mail", "email"));
	colunas.add(new Coluna<Cliente>("Endereço", "endereco.enderecoFormatado"));

	Tabela<Cliente> tabela = new Tabela<Cliente>("dtClientes", colunas, new ClientesProvider(), 20);

	add(tabela);
    }

    private class ClientesProvider extends ProviderGenerico<Cliente> {

	private static final long serialVersionUID = -7569110715710900773L;

	@Override
	public Iterator<? extends Cliente> iterator(long first, long count) {
	    return repository.findAll().iterator();
	}

	@Override
	public long size() {
	    return repository.count();
	}
    }
}