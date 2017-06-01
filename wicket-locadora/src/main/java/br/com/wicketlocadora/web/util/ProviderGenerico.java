package br.com.wicketlocadora.web.util;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import br.com.wicketlocadora.persistence.domain.Entidade;

public abstract class ProviderGenerico<T extends Entidade> implements IDataProvider<T> {

    private static final long serialVersionUID = -5994112770727172538L;

    @Override
    public void detach() {
	// TODO Auto-generated method stub

    }

    @Override
    public IModel<T> model(T object) {
	return Model.of(object);
    }

}
