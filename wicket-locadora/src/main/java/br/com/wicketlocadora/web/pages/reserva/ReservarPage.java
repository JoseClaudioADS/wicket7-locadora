package br.com.wicketlocadora.web.pages.reserva;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.wicketlocadora.service.exception.NegocioException;
import br.com.wicketlocadora.service.reserva.ReservaService;
import br.com.wicketlocadora.web.pages.template.RaizPage;
import br.com.wicketlocadora.web.util.dto.ReservaDTO;

public class ReservarPage extends RaizPage {

    private static final long serialVersionUID = -5846093840682902366L;

    private ReservaDTO reserva;
    private CompoundPropertyModel<ReservaDTO> reservaModel;

    @SpringBean
    private ReservaService reservaService;

    public ReservarPage() {
	this.reserva = new ReservaDTO();
	this.reservaModel = new CompoundPropertyModel<ReservaDTO>(reserva);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	Form<ReservaDTO> formulario = new Form<ReservaDTO>("formulario", reservaModel);
	add(formulario);
	formulario.add(new DadosReservaPanel("painelDadosReserva", reservaModel));
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
