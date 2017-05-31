package br.com.wicketlocadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Aplicacao {

    public static void main(String[] args) {
	SpringApplication.run(Aplicacao.class, args);
    }
}
