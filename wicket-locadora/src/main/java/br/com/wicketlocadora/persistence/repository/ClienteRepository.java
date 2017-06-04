package br.com.wicketlocadora.persistence.repository;

import java.util.List;

import javax.jdo.annotations.Cacheable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.wicketlocadora.persistence.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, QueryDslPredicateExecutor<Cliente> {

    Cliente findByNome(String nome);

    @Cacheable("nomesClientes")
    @Query("select c.nome from Cliente c where lower(c.nome) like lower(concat(?1, '%')) order by c.nome asc")
    List<String> buscarNomes(String nome);

}