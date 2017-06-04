package br.com.wicketlocadora.web.pages.reserva;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AbstractAutoCompleteRenderer;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.persistence.repository.ClienteRepository;
import br.com.wicketlocadora.web.pages.filmes.ListaFilmesPanel;
import br.com.wicketlocadora.web.util.dto.ReservaDTO;
import br.com.wicketlocadora.web.util.tabela.Coluna;

public class DadosReservaPanel extends Panel {

    private static final long serialVersionUID = -2202503537333654225L;

    private AutoCompleteTextField<String> autoCompleteCliente;
    private CompoundPropertyModel<ReservaDTO> reservaModel;

    private WebMarkupContainer markupRepetidor;
    private ListView<String> filmesSelecionados;

    private List<String> titulosFilmes;

    @SpringBean
    private ClienteRepository clienteRepository;

    public DadosReservaPanel(String id, CompoundPropertyModel<ReservaDTO> reservaModel) {
	super(id);
	this.reservaModel = reservaModel;
	this.titulosFilmes = new ArrayList<String>();
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	markupRepetidor = new WebMarkupContainer("markupRepetidor");
	markupRepetidor.setOutputMarkupId(true);
	add(markupRepetidor);
	filmesSelecionados = new ListView<String>("filmesSelecionados", titulosFilmes) {

	    @Override
	    protected void populateItem(ListItem<String> item) {

		titulosFilmes.forEach(f -> {
		    item.add(new Label("titulo", f));
		    item.add(new AjaxLink<String>("linkRemover") {

			@Override
			public void onClick(AjaxRequestTarget target) {
			    titulosFilmes.remove(f);
			    target.add(markupRepetidor);
			}
		    });
		});

	    }
	};
	filmesSelecionados.setReuseItems(true);
	filmesSelecionados.setOutputMarkupId(true);
	markupRepetidor.add(filmesSelecionados);

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
	addListaFilmes();
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

    private void addListaFilmes() {
	// Reusando o painel de filmes e adicionando um botão de inclusão
	ListaFilmesPanel listaFilmes = new ListaFilmesPanel("listaFilmesPanel") {
	    @Override
	    public void addColunasExtras(List<Coluna<Filme>> colunas) {
		colunas.add(new Coluna<Filme>("Ações", "") {

		    @Override
		    public void populateItem(Item<ICellPopulator<Filme>> cellItem, String componentId,
			    IModel<Filme> rowModel) {
			cellItem.add(new BotaoIncluirFilmePanel(componentId) {

			    @Override
			    protected void acaoBotao(AjaxRequestTarget target) {
				if (CollectionUtils
					.isEmpty(DadosReservaPanel.this.reservaModel.getObject().getIdsFilmes())) {
				    DadosReservaPanel.this.reservaModel.getObject()
					    .setIdsFilmes(new LinkedHashSet<Long>());
				}

				Filme object = rowModel.getObject();
				if (!DadosReservaPanel.this.reservaModel.getObject().getIdsFilmes()
					.contains(object.getId())) {
				    DadosReservaPanel.this.reservaModel.getObject().getIdsFilmes().add(object.getId());

				    titulosFilmes.add(object.getTitulo());
				    target.add(markupRepetidor);
				}
			    }
			});
		    }
		});
	    }
	};

	add(listaFilmes);
    }

}