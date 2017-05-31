package br.com.wicketlocadora.web.pages.template;

import java.util.List;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class RaizPage extends WebPage {

    private static final long serialVersionUID = -6836630541254981348L;

    @Override
    protected void onInitialize() {
	super.onInitialize();
	FeedbackPanel feedbackPanel = new FeedbackPanel("feedback") {
	    @Override
	    protected String getCSSClass(FeedbackMessage message) {
		String css;
		switch (message.getLevel()) {
		case FeedbackMessage.SUCCESS:
		    css = "alert alert-success";
		    break;
		case FeedbackMessage.INFO:
		    css = "alert alert-info";
		    break;
		case FeedbackMessage.ERROR:
		    css = "alert-danger";
		    break;
		default:
		    css = "alert";
		}

		return css;
	    }
	};
	add(feedbackPanel);
	add(new MenuPanel("painelMenu"));
    }

    protected void lancarErros(List<String> erros) {
	erros.stream().forEach(e -> error(e));
    }
}
