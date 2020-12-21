package com.github.dearrudam.seeddesafiocdc.autor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api/v1/autores")
public class NovoAutorController {

    private final EntityManager manager;

    public NovoAutorController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<NovoAutorResponse> execute
            (@RequestBody @Valid NovoAutorRequest
                     request) {
        Autor novoAutor = request.toModel();
        manager.persist(novoAutor);
        return ResponseEntity.status(OK).body(new NovoAutorResponse(novoAutor));
    }

}