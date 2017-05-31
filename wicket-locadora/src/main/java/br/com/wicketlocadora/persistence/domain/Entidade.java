package br.com.wicketlocadora.persistence.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public abstract class Entidade implements Serializable {

    private static final long serialVersionUID = -5530508746962111162L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer versao;

    @Column(name = "data_hora_criacao", nullable = false, updatable = false)
    @CreatedDate
    private long dataHoraCriacao;

    @Column(name = "data_hora_ultima_atualizacao")
    @LastModifiedDate
    private long dataHoraUltimaAtualizacao;

}
