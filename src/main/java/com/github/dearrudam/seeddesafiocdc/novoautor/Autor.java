package com.github.dearrudam.seeddesafiocdc.novoautor;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Instant instante;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String nome;
    @NotEmpty
    @Size(min = 1, max = 400)
    @Lob
    private String descricao;

    /**
     * DO NOT USE! This constructor is required by JPA 
     */
    @Deprecated
    public Autor() {
    }

    public Autor(
            @NotNull @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC") Instant instante,
            @Email @NotEmpty String email,
            @NotEmpty String nome,
            @NotEmpty @Size(min = 1, max = 400) String descricao) {

        this.instante = instante;
        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        Assert.notNull(this.id, "não é possível retornar o ID de uma entidade não persistida. Você salvou esta entidade?");
        return this.id;
    }
}
