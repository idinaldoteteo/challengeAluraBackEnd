package org.teteo.aluraFlix.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.teteo.aluraFlix.dto.CategoriaDTO;
import org.teteo.aluraFlix.dto.VideoDTO;
import org.teteo.aluraFlix.form.CategoriaForm;
import org.teteo.aluraFlix.model.Categoria;
import org.teteo.aluraFlix.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public List<CategoriaDTO> getTodasCategorias() {
		return service.getTodasCategorias();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> getCategoria(@PathVariable Long id) {
		Optional<Categoria> categoria = service.getCategoria(id);
		
		if( categoria.isPresent()) {
			return ResponseEntity.ok(new CategoriaDTO(categoria.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/videos")
	public List<VideoDTO> getVideosPorCategoria(@PathVariable Long id) {
		Optional<Categoria> categoria = service.getCategoria(id);
		if(categoria.isPresent()) {
			return service.getVideosPorCategoria(id);
		}
		
		return new ArrayList<>();
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
		CategoriaDTO categoriaDTO = service.cadastrar(form);
		
		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoriaDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).body(categoriaDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaForm form) {
		Optional<Categoria> categoria = service.getCategoria(id);
		
		if(categoria.isPresent()) {
			return ResponseEntity.ok(service.atualizar(id, form));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CategoriaDTO> remover(@PathVariable Long id) {
		Optional<Categoria> categoria = service.getCategoria(id);
		
		if(categoria.isPresent()) {
			service.deletar(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
