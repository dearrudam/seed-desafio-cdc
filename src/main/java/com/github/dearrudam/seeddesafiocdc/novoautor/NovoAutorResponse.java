package com.github.dearrudam.seeddesafiocdc.novoautor;

public class NovoAutorResponse {

    private final Long id;

    public NovoAutorResponse(Autor autor) {
        this.id = autor.getId();
    }

    public Long getId() {
        return id;
    }
}
