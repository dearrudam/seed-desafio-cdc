package com.github.dearrudam.seeddesafiocdc.livro;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.dearrudam.seeddesafiocdc.categoria.Categoria;
import com.github.dearrudam.seeddesafiocdc.autor.Autor;
import com.github.dearrudam.seeddesafiocdc.validation.Unique;
import com.github.dearrudam.seeddesafiocdc.validation.ValidId;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CriarLivroRequest {

    @NotEmpty
    @Unique(entityClass = Livro.class, fieldName = "titulo")
    private final String titulo;

    @NotEmpty
    private final String sumario;

    @Size(min = 1, max = 500)
    private final String resumo;

    @NotEmpty
    @Unique(entityClass = Livro.class, fieldName = "isbn")
    private final String isbn;

    @NotNull
    @Future
    private final LocalDate dataLancamento;

    @NotNull
    @Min(100)
    private final Integer numeroDePaginas;

    @NotNull
    @Min(20)
    private final BigDecimal preco;

    @NotNull
    @ValidId(entityClass = Categoria.class, message = "categoria informada não é válida")
    private final Long categoriaId;

    @NotNull
    @ValidId(entityClass = Autor.class, message = "autor informado não é válida")
    private final Long autorId;

    @JsonCreator(mode = Mode.PROPERTIES)
    public CriarLivroRequest(
            @NotEmpty @Unique(entityClass = Livro.class, fieldName = "titulo") String titulo,
            @NotEmpty String sumario, @Size(min = 1, max = 500) String resumo,
            @NotEmpty @Unique(entityClass = Livro.class, fieldName = "isbn") String isbn,
            @NotNull @Future @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dataLancamento,
            @NotNull @Min(100) Integer numeroDePaginas,
            @NotNull @Min(20) BigDecimal preco,
            @NotNull
            @ValidId(entityClass = Categoria.class, message = "categoria informada não é válida") Long categoriaId,
            @NotNull
            @ValidId(entityClass = Autor.class, message = "autor informado não é válida") Long autorId) {
        super();
        this.titulo = titulo;
        this.sumario = sumario;
        this.resumo = resumo;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
        this.numeroDePaginas = numeroDePaginas;
        this.preco = preco;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public Livro toModel(EntityManager manager) {
        Livro livro = Livro
                .builder()
                .autor(manager.find(Autor.class, this.autorId))
                .categoria(manager.find(Categoria.class, this.categoriaId))
                .titulo(this.titulo)
                .sumario(this.sumario)
                .resumo(this.resumo)
                .isbn(this.isbn)
                .numeroDePaginas(this.numeroDePaginas)
                .dataLancamento(this.dataLancamento)
                .preco(this.preco)
                .build();
        return livro;
    }

}
