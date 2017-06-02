package br.com.wicketlocadora.web.pages.cliente;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import br.com.wicketlocadora.persistence.domain.Cliente;
import br.com.wicketlocadora.service.cliente.ClienteService;
import br.com.wicketlocadora.service.exception.NegocioException;
import br.com.wicketlocadora.web.pages.template.RaizPage;

public class ManterClientePage extends RaizPage {

    private static final long serialVersionUID = -1155698374256154557L;

    private Cliente cliente;
    private CompoundPropertyModel<Cliente> clienteModel;

    @SpringBean
    private ClienteService clienteService;

    public ManterClientePage(PageParameters parameters) {
	StringValue id = parameters.get("id");
	if (id.isNull()) {
	    this.cliente = new Cliente();
	} else {
	    this.cliente = clienteService.getRepository().findOne(id.toLongObject());
	}
	clienteModel = new CompoundPropertyModel<Cliente>(cliente);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	add(new Label("titulo", cliente.getId() == null ? "Novo" : "Alterar"));
	Form<Cliente> formulario = new Form<Cliente>("formulario", clienteModel);
	add(formulario);
	formulario.add(new DadosClientePanel("painelDadosCliente", clienteModel));
	formulario.add(new Button("btnSalvar") {
	    @Override
	    public void onSubmit() {
		try {
		    clienteService.manter(cliente);
		    getSession().success("Cliente cadastrado com sucesso.");
		    setResponsePage(ClientesPage.class);
		} catch (NegocioException e) {
		    lancarErros(e.getErros());
		}
	    }
	});
    }
}
