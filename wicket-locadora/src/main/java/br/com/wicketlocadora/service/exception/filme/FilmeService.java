package br.com.wicketlocadora.service.exception.filme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wicketlocadora.persistence.domain.Categoria;
import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.persistence.repository.CategoriaRepository;
import br.com.wicketlocadora.persistence.repository.FilmeRepository;
import br.com.wicketlocadora.service.IService;
import br.com.wicketlocadora.service.exception.NegocioException;
import br.com.wicketlocadora.service.filme.ValidadorFilme;

@Service
public class FilmeService implements IService<Filme> {

    public static final String NOME_PASTA_CAPAS = "capas";
    public static final String LOCAL_UPLOAD_CAPAS = "src/main/webapp/" + NOME_PASTA_CAPAS;

    @Autowired
    private FilmeRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public void manter(Filme filme, FileUpload capa) throws NegocioException {
	ValidadorFilme validador = new ValidadorFilme();
	validador.validarFilme(filme);
	validador.validarCapa(capa);

	// o wicket nos traz as categorias de forma transient
	// a utilização do padrão openSessionInView poderia ajudar, mas...
	// ele é um Anti Pattern, vide:
	// https://vladmihalcea.com/2016/05/30/the-open-session-in-view-anti-pattern/
	List<Categoria> categorias = new ArrayList<Categoria>();
	for (Categoria categoria : filme.getCategorias()) {
	    categorias.add(categoriaRepository.findOne(categoria.getId()));
	}
	filme.setCategorias(categorias);
	filme.setCapa(uploadCapa(capa));
	repository.save(filme);
    }

    private String uploadCapa(FileUpload capa) {

	try {
	    Path diretorio = Paths.get(LOCAL_UPLOAD_CAPAS);
	    if (!Files.exists(diretorio)) {
		Files.createDirectory(diretorio);
	    }

	    String nomeArquivo = new Date().getTime() + capa.getClientFileName();
	    Path pathArquivo = Paths.get(LOCAL_UPLOAD_CAPAS + "\\" + nomeArquivo);
	    Files.createFile(pathArquivo);

	    Files.write(pathArquivo, capa.getBytes());

	    return nomeArquivo;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public JpaRepository<Filme, Long> getRepository() {
	return repository;
    }

}
