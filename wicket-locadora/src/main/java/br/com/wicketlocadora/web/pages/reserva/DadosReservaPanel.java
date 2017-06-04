package br.com.wicketlocadora.web.pages.reserva;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

public class DadosReservaPanel extends Panel {

    private static final long serialVersionUID = 4837375229035068057L;

    public DadosReservaPanel(String id) {
	super(id);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	addCamposData();

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