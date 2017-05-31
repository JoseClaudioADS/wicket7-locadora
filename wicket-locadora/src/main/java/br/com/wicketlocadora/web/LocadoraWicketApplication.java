package br.com.wicketlocadora.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import br.com.wicketlocadora.web.pages.HomePage;

public class LocadoraWicketApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
	return HomePage.class;
    }

    @Override
    protected void init() {
	super.init();
	getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }

}
