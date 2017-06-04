package br.com.wicketlocadora.persistence.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.wicketlocadora.persistence.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Cacheable("categorias")
    @Query("from Categoria c order by c.nome asc")
    List<Categoria> todasAsCategorias();

}
