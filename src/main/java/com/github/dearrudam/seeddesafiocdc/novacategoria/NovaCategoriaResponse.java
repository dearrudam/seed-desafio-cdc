package com.github.dearrudam.seeddesafiocdc.novacategoria;

public class NovaCategoriaResponse {

	private Long categoriaId;

	public NovaCategoriaResponse(CategoriaEntity novaCategoria) {
		this.categoriaId = novaCategoria.getId();
	}

	public Long getCategoriaId() {
		return categoriaId;
	}
}
