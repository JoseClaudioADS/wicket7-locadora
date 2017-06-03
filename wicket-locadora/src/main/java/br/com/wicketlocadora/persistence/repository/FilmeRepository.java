package br.com.wicketlocadora.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wicketlocadora.persistence.domain.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

}
