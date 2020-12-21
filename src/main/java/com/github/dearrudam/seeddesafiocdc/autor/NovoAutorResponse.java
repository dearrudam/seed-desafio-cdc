package com.github.dearrudam.seeddesafiocdc.autor;

public class NovoAutorResponse {

    private final Long id;

    public NovoAutorResponse(Autor autor) {
        this.id = autor.getId();
    }

    public Long getId() {
        return id;
    }
}
