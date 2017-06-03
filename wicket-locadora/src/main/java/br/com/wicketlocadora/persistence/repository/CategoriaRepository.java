package br.com.wicketlocadora.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.wicketlocadora.persistence.domain.Categoria;

@Repository
@Transactional(readOnly = true)
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
