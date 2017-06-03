package br.com.wicketlocadora.service.exception.filme;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private FilmeRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public void manter(Filme filme) throws NegocioException {
	ValidadorFilme validador = new ValidadorFilme();
	validador.validarFilme(filme);

	// o wicket nos traz as categorias de forma transient
	// a utilização do padrão openSessionInView poderia ajudar, mas...
	// ele é um Anti Pattern, vide:
	// https://vladmihalcea.com/2016/05/30/the-open-session-in-view-anti-pattern/
	List<Categoria> categorias = new ArrayList<Categoria>();
	for (Categoria categoria : filme.getCategorias()) {
	    categorias.add(categoriaRepository.findOne(categoria.getId()));
	}
	filme.setCategorias(categorias);
	repository.save(filme);
    }

    @Override
    public JpaRepository<Filme, Long> getRepository() {
	return repository;
    }

}
