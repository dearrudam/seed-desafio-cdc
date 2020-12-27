package com.github.dearrudam.seeddesafiocdc.paises;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.dearrudam.seeddesafiocdc.validation.Unique;

import javax.validation.constraints.NotEmpty;

public class NovoPaisRequest {

    @NotEmpty
    @Unique(entityClass = Pais.class, fieldName = "nome")
    private final String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoPaisRequest(
            @NotEmpty @Unique(entityClass = Pais.class, fieldName = "nome") String nome) {
        this.nome = nome;
    }


    public Pais toModel() {
        return new Pais(this.nome);
    }
}
