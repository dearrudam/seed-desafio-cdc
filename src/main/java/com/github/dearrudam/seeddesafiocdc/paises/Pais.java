package com.github.dearrudam.seeddesafiocdc.paises;

import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    /**
     * DO NOT USE! It's required by JPA
     */
    @Deprecated
    public Pais() {

    }

    public Pais(@NotEmpty String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        Assert.notNull(this.id,"Entidade não persistida");
        return this.id;
    }
}
