package com.github.dearrudam.seeddesafiocdc.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.Map;

@RestController
@Validated
public class ExibirDetalheDoLivroController {

    private final EntityManager manager;

    public ExibirDetalheDoLivroController(EntityManager manager) {
        this.manager = manager;
    }

    @GetMapping("/api/v1/livros/{livroId}")
    public ResponseEntity<?> get(@PathVariable("livroId")
                                         Long livroId) {
        Livro livro = manager.find(Livro.class, livroId);
        if (livro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                Map.of(
                        "id", livro.getId(),
                        "titulo", livro.getTitulo(),
                        "resumo", livro.getResumo(),
                        "sumario", livro.getSumario(),
                        "isbn", livro.getIsbn(),
                        "preco", livro.getPreco(),
                        "autor", Map.of(
                                "id", livro.getAutor().getId(),
                                "nome", livro.getAutor().getNome(),
                                "descricao", livro.getAutor().getDescricao()
                        ),
                        "categoria", Map.of(
                                "id", livro.getCategoria().getId(),
                                "nome", livro.getCategoria().getNome()
                        ))
        );
    }

}
