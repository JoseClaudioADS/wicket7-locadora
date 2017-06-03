package br.com.wicketlocadora.service;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wicketlocadora.persistence.domain.Entidade;

public interface IService<T extends Entidade> {

    JpaRepository<T, Long> getRepository();

}
