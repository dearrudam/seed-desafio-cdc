package com.github.dearrudam.seeddesafiocdc.estado;

import lombok.Getter;

@Getter
public class NovoEstadoResponse {

    private final Long id;

    private final String nome;

    public NovoEstadoResponse(Estado novoEstado) {
        this.id = novoEstado.getId();
        this.nome = novoEstado.getNome();
    }
}
