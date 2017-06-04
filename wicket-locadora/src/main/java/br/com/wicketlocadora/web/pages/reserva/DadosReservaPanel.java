package br.com.wicketlocadora.web.pages.reserva;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Locale;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AbstractAutoCompleteRenderer;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import br.com.wicketlocadora.persistence.repository.ClienteRepository;

public class DadosReservaPanel extends Panel {

    private static final long serialVersionUID = -2202503537333654225L;

    private AutoCompleteTextField<String> autoCompleteCliente;

    @SpringBean
    private ClienteRepository clienteRepository;

    public DadosReservaPanel(String id) {
	super(id);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	addCamposData();

	autoCompleteCliente = new AutoCompleteTextField<String>("cliente", new AbstractAutoCompleteRenderer<String>() {

	    @Override
	    protected void renderChoice(String object, Response response, String criteria) {
		response.write(getTextValue(object));
	    }

	    @Override
	    protected String getTextValue(String object) {
		return object;
	    }
	}) {

	    @Override
	    protected Iterator<String> getChoices(String nome) {
		return clienteRepository.buscarNomes(nome).iterator();
	    }

	    @Override
	    public void onEvent(IEvent<?> event) {
		super.onEvent(event);
	    }

	};

	add(autoCompleteCliente);

    }

    private void addCamposData() {

	add(criarCampoData("dataInicio"));
	add(criarCampoData("dataFinal"));

    }

    private DateTextField criarCampoData(String id) {

	DateTextField dateField = new DateTextField(id, new StyleDateConverter("S-", true)) {
	    @Override
	    public <C> IConverter<C> getConverter(Class<C> type) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		return new IConverter<C>() {

		    @Override
		    public C convertToObject(String arg0, Locale arg1) throws ConversionException {
			return (C) LocalDate.parse(arg0, formatter);
		    }

		    @Override
		    public String convertToString(C arg0, Locale arg1) {
			return ((LocalDate) arg0).format(formatter);
		    }
		};
	    }
	};

	DatePicker datePicker = new DatePicker() {
	    @Override
	    protected String getAdditionalJavaScript() {
		return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
	    }
	};
	datePicker.setShowOnFieldClick(true);
	datePicker.setAutoHide(true);
	dateField.add(datePicker);
	return dateField;
    }

}