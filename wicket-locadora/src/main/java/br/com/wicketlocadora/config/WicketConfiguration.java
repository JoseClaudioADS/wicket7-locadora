package br.com.wicketlocadora.config;

import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import br.com.wicketlocadora.web.LocadoraWicketApplication;

@Configuration
public class WicketConfiguration {

    @Order(1)
    @Bean
    public FilterRegistrationBean filtroWicket() {
	FilterRegistrationBean registro = new FilterRegistrationBean();
	registro.setFilter(new WicketFilter(new LocadoraWicketApplication()));
	registro.addInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
	registro.addUrlPatterns("/*");
	return registro;
    }

}
