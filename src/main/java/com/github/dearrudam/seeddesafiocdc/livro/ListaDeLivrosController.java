package com.github.dearrudam.seeddesafiocdc.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/livros")
@Validated
public class ListaDeLivrosController {

    private final LivroRepository repository;

    public ListaDeLivrosController(LivroRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Page<Map<String, Object>> executar(@RequestParam(value = "pageNumber", defaultValue = "0")
                                              @PositiveOrZero int pageNumber,
                                              @RequestParam(value = "pageSize", defaultValue = "50")
                                              @PositiveOrZero @Max(500) int pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize))
                .map(livro -> Map.of("id", livro.getId(), "titulo", livro.getTitulo()));
    }

}
