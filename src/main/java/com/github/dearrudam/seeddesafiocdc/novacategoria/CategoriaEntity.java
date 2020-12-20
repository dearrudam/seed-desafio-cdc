package com.github.dearrudam.seeddesafiocdc.novacategoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.util.Assert;

@Entity
public class CategoriaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nome;

	public CategoriaEntity(@NotEmpty String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		Assert.notNull(id,"falha ao retornar o ID de uma categoria não persistida.");
		return id;
	}

}
