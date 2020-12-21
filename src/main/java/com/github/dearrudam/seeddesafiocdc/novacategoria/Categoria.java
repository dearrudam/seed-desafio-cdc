package com.github.dearrudam.seeddesafiocdc.novacategoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.util.Assert;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nome;

	/**
	 * DO NOT USE! It's required by JPA
	 */
	@Deprecated
	public Categoria() {
		super();
	}

	public Categoria(@NotEmpty String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		Assert.notNull(id, "falha ao retornar o ID de uma categoria não persistida.");
		return id;
	}

}
