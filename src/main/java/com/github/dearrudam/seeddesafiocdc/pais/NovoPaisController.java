package com.github.dearrudam.seeddesafiocdc.pais;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/paises")
public class NovoPaisController {

    private final EntityManager entityManager;

    public NovoPaisController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public NovoPaisResponse execute(@RequestBody @Valid NovoPaisRequest novoPaisRequest){
        Pais novoPais=novoPaisRequest.toModel();
        entityManager.persist(novoPais);
        return new NovoPaisResponse(novoPais);
    }

}
