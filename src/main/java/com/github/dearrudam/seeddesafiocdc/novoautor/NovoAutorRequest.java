package com.github.dearrudam.seeddesafiocdc.novoautor;

import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.dearrudam.seeddesafiocdc.validation.Unique;

public class NovoAutorRequest {
    @NotNull
    @JsonFormat( pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private final Instant instante;
    @NotEmpty
    @Email
    @Unique(entityClass = AutorEntity.class, fieldName = "email")
    private final String email;
    @NotEmpty
    private final String nome;
    @NotEmpty
    @Size(min = 1, max = 400)
    private final String descricao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoAutorRequest(
            @NotNull 
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC") Instant instante,
            @Email
            @NotEmpty
            @Unique(entityClass = AutorEntity.class, fieldName = "email") String email,
            @NotEmpty String nome,
            @NotEmpty @Size(min = 1, max = 400) String descricao
    ) {

        this.instante = instante;
        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
    }

    public AutorEntity toModel() {
        return new AutorEntity(
                instante,
                email,
                nome,
                descricao
        );
    }
}
