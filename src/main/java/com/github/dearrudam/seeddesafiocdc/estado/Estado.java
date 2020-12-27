package com.github.dearrudam.seeddesafiocdc.estado;

import com.github.dearrudam.seeddesafiocdc.pais.Pais;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotNull
    @ManyToOne
    private Pais pais;

    /**
     * DO NOT USE! It's required by JPA
     */
    @Deprecated
    public Estado() {
    }

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Long getId() {
        Assert.notNull(this.id,"entidade não persistida!");
        return id;
    }

    public String getNome() {
        return nome;
    }
}
