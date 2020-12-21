package com.github.dearrudam.seeddesafiocdc.livro;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/livros")
public class CriarLivroController {

    private final EntityManager manager;

    public CriarLivroController(EntityManager manager) {
        super();
        this.manager = manager;
    }

    @PostMapping
    @ResponseBody
    @Transactional
    public CriarLivroResponse executar(
            @RequestBody
            @Valid
                    CriarLivroRequest request) {

        Livro novoLivro = request.toModel(manager);
        manager.persist(novoLivro);
        return new CriarLivroResponse(novoLivro);
    }

}
