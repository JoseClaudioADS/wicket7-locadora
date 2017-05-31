package br.com.wicketlocadora.web.pages.cliente;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import br.com.wicketlocadora.persistence.domain.Cliente;
import br.com.wicketlocadora.persistence.domain.enumeracoes.UF;

public class DadosClientePanel extends Panel {

    private static final long serialVersionUID = 3275723085860881563L;

    private CompoundPropertyModel<Cliente> clienteModel;

    public DadosClientePanel(String id, CompoundPropertyModel<Cliente> clienteModel) {
	super(id);
	this.clienteModel = clienteModel;
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	add(new TextField<String>("nome"));
	add(new TextField<String>("email"));
	add(new TextField<String>("logradouro", clienteModel.bind("endereco.logradouro")));
	add(new TextField<String>("cidade", clienteModel.bind("endereco.cidade")));
	add(new DropDownChoice<UF>("uf", clienteModel.bind("endereco.uf"), Arrays.asList(UF.values())));

    }

}