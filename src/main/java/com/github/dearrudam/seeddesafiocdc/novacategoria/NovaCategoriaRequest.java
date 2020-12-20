package com.github.dearrudam.seeddesafiocdc.novacategoria;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.github.dearrudam.seeddesafiocdc.validation.Unique;

public class NovaCategoriaRequest {

	@NotEmpty 
	@Unique(entityClass = CategoriaEntity.class, fieldName = "nome")
	private final String nome;

	@JsonCreator(mode = Mode.PROPERTIES)
	public NovaCategoriaRequest(
			@NotEmpty @Unique(entityClass = CategoriaEntity.class, fieldName = "nome") String nome) {
		super();
		this.nome = nome;
	}

	public CategoriaEntity toModel() {
		return new CategoriaEntity(this.nome);
	}

}
