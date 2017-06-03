package br.com.wicketlocadora.web.util;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.wicketlocadora.persistence.domain.Entidade;

public class LinkImagemPanel<T extends Entidade> extends Panel {

    private static final long serialVersionUID = 7208199948930770336L;

    private String localImagem;
    private String altura;
    private String largura;
    private Class pagina;
    private PageParameters parameters;

    public LinkImagemPanel(String id, String localImagem, String altura, String largura, Class pagina,
	    PageParameters parameters) {
	super(id);
	this.localImagem = localImagem;
	this.pagina = pagina;
	this.altura = altura;
	this.largura = largura;
	this.parameters = parameters;
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	BookmarkablePageLink<T> link = new BookmarkablePageLink<T>("link", this.pagina, this.parameters);
	link.add(new ContextImage("imagem", this.localImagem) {
	    @Override
	    protected void onConfigure() {
		super.onConfigure();
		add(new AttributeModifier("height", LinkImagemPanel.this.altura));
		add(new AttributeModifier("width", LinkImagemPanel.this.largura));
	    }
	});
	add(link);
    }
}
