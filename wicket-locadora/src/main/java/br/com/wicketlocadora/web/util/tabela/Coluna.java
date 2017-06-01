package br.com.wicketlocadora.web.util.tabela;

import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;

import br.com.wicketlocadora.persistence.domain.Entidade;

public class Coluna<T extends Entidade> extends PropertyColumn<T, String> {

    public Coluna(String cabecalho, String propriedade) {
	super(new Model<String>(cabecalho), propriedade);
    }

}
