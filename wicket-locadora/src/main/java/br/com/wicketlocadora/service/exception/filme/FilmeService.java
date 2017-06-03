package br.com.wicketlocadora.service.exception.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wicketlocadora.persistence.domain.Filme;
import br.com.wicketlocadora.persistence.repository.FilmeRepository;
import br.com.wicketlocadora.service.IService;
import br.com.wicketlocadora.service.exception.NegocioException;
import br.com.wicketlocadora.service.filme.ValidadorFilme;

@Service
public class FilmeService implements IService<Filme> {

    @Autowired
    private FilmeRepository repository;

    @Transactional
    public void manter(Filme filme) throws NegocioException {
	ValidadorFilme validador = new ValidadorFilme();
	validador.validarFilme(filme);

	// repository.save(filme);
    }

    @Override
    public JpaRepository<Filme, Long> getRepository() {
	return repository;
    }

}
