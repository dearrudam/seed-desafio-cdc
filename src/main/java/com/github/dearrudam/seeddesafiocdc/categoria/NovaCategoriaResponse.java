package com.github.dearrudam.seeddesafiocdc.categoria;

public class NovaCategoriaResponse {

	private Long categoriaId;

	public NovaCategoriaResponse(Categoria novaCategoria) {
		this.categoriaId = novaCategoria.getId();
	}

	public Long getCategoriaId() {
		return categoriaId;
	}
}
