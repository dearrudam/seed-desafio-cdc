package com.github.dearrudam.seeddesafiocdc.pais;

import lombok.Getter;

@Getter
public class NovoPaisResponse {

    private final Long id;
    private final String nome;

    public NovoPaisResponse(Pais novoPais) {

        this.id = novoPais.getId();
        this.nome = novoPais.getNome();
    }
}
