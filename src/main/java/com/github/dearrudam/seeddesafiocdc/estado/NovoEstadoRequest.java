package com.github.dearrudam.seeddesafiocdc.estado;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.dearrudam.seeddesafiocdc.pais.Pais;
import com.github.dearrudam.seeddesafiocdc.validation.Unique;
import com.github.dearrudam.seeddesafiocdc.validation.ValidId;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovoEstadoRequest {

    @NotNull
    @ValidId(entityClass = Pais.class)
    private final Long paisId;

    @NotEmpty
    @Unique(entityClass = Estado.class, fieldName = "nome")
    private final String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoEstadoRequest(@NotNull @ValidId(entityClass = Pais.class) Long paisId,
                             @NotEmpty @Unique(entityClass = Estado.class, fieldName = "nome") String nome) {
        this.paisId = paisId;
        this.nome = nome;
    }

    public Estado toModel(EntityManager entityManager) {
        return new Estado(nome, entityManager.find(Pais.class, this.paisId));
    }
}
