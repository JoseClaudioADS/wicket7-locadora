package br.com.wicketlocadora.web.pages.cliente;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import br.com.wicketlocadora.persistence.domain.Cliente;
import br.com.wicketlocadora.web.pages.template.RaizPage;

public class ManterClientePage extends RaizPage {

    private static final long serialVersionUID = -1155698374256154557L;

    private Cliente cliente;
    private CompoundPropertyModel<Cliente> clienteModel;

    public ManterClientePage() {
	cliente = new Cliente();
	clienteModel = new CompoundPropertyModel<Cliente>(cliente);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	Form<Cliente> formulario = new Form<Cliente>("formulario", clienteModel);
	add(formulario);
	formulario.add(new DadosClientePanel("painelDadosCliente", clienteModel));
	formulario.add(new Button("btnSalvar") {
	    @Override
	    public void onSubmit() {
		System.out.println(cliente);
	    }
	});
    }

}
