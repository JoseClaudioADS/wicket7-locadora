package br.com.wicketlocadora.config;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.wicketlocadora.persistence.repository.FilmeRepository;
import br.com.wicketlocadora.service.exception.filme.FilmeService;

@Configuration
public class ScheduleConfig {

    @Autowired
    private FilmeRepository filmeRepository;

    // setando para a cada 1 minuto. Mas provavelmente será mais tempo em um
    // caso real.
    // talvez se a gama de alterações for muita, um Batch seria melhor
    @Scheduled(fixedRate = 60000, initialDelay = 10000)
    public void limparCapasNaoUsadas() {
	List<String> capas = filmeRepository.buscarCapas();
	System.out.println(capas);
	try {
	    DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(FilmeService.LOCAL_UPLOAD_CAPAS));
	    for (Path path : stream) {
		if (!capas.contains(path.getFileName().toString())) {
		    Files.delete(path);
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
