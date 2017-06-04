package br.com.wicketlocadora.web.pages.reserva;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.wicketlocadora.persistence.domain.Reserva;
import br.com.wicketlocadora.service.exception.NegocioException;
import br.com.wicketlocadora.service.reserva.ReservaService;
import br.com.wicketlocadora.web.pages.template.RaizPage;

public class ReservarPage extends RaizPage {

    private static final long serialVersionUID = -5846093840682902366L;

    private Reserva reserva;
    private CompoundPropertyModel<Reserva> reservaModel;

    @SpringBean
    private ReservaService reservaService;

    public ReservarPage() {
	this.reserva = new Reserva();
	this.reservaModel = new CompoundPropertyModel<Reserva>(reserva);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	Form<Reserva> formulario = new Form<Reserva>("formulario", reservaModel);
	add(formulario);
	formulario.add(new DadosReservaPanel("painelDadosReserva"));
	formulario.add(new Button("btnReservar") {
	    @Override
	    public void onSubmit() {
		try {
		    reservaService.reservar(reserva);
		    getSession().success("Reserva realizada com sucesso.");
		    setResponsePage(ReservasPage.class);
		} catch (NegocioException e) {
		    lancarErros(e.getErros());
		}
	    }
	});
    }

}
