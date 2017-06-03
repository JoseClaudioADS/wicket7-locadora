package br.com.wicketlocadora.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.wicketlocadora.persistence.domain.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    @Query("select f.capa from Filme f")
    List<String> buscarCapas();

}
