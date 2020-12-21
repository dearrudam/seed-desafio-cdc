package com.github.dearrudam.seeddesafiocdc.livro;

import lombok.Data;

@Data
public class CriarLivroResponse {

	private Long livroId;

	public CriarLivroResponse(Livro novoLivro) {
		this.livroId = novoLivro.getId();
	}

}
