package br.com.wicketlocadora.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.wicketlocadora.persistence.domain.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>, QueryDslPredicateExecutor<Reserva> {

}
