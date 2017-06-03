package br.com.wicketlocadora.web.pages.filmes;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.Sort;

import br.com.wicketlocadora.persistence.domain.Categoria;
import br.com.wicketlocadora.persistence.repository.CategoriaRepository;
import lombok.Getter;

public class DadosFilmePanel extends Panel {

    private static final long serialVersionUID = -3618001620594800540L;

    @Getter
    private FileUploadField capa;

    @SpringBean
    private CategoriaRepository categoriaRepository;

    public DadosFilmePanel(String id) {
	super(id);
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	add(new TextField<String>("titulo"));
	add(new TextArea<String>("descricao"));

	ListMultipleChoice<Categoria> lmCategorias = new ListMultipleChoice<Categoria>("categorias",
		categoriaRepository.findAll(new Sort("nome")), new ChoiceRenderer<Categoria>("nome", "id"));
	add(lmCategorias);
	capa = new FileUploadField("capa");
	add(capa);// campo input file

    }

}