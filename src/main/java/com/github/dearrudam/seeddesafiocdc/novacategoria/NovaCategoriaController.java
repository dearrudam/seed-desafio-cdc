package com.github.dearrudam.seeddesafiocdc.novacategoria;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categorias")
public class NovaCategoriaController {
	
	private final EntityManager manager;
	

	public NovaCategoriaController(EntityManager manager) {
		super();
		this.manager = manager;
	}


	@PostMapping
	@Transactional
	@ResponseBody
	public NovaCategoriaResponse execute(
			@RequestBody 
			@Valid
			NovaCategoriaRequest request){
	
		CategoriaEntity novaCategoria = request.toModel();
		
		manager.persist(novaCategoria);
		
		return new NovaCategoriaResponse(novaCategoria);
		
	}
	
}
