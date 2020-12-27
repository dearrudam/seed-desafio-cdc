package com.github.dearrudam.seeddesafiocdc.estado;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/estados")
public class NovoEstadoController {

    private final EntityManager entityManager;

    public NovoEstadoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public NovoEstadoResponse executar (@RequestBody @Valid NovoEstadoRequest novoEstadoRequest){
        Estado novoEstado=novoEstadoRequest.toModel(entityManager);
        entityManager.persist(novoEstado);
        return new NovoEstadoResponse(novoEstado);
    }
}
