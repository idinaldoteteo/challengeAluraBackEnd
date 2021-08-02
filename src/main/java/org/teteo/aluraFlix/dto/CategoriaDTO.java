package org.teteo.aluraFlix.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.teteo.aluraFlix.model.Categoria;

public class CategoriaDTO {

	private Long id;
	private String titulo;
	private String cor;
	
	public CategoriaDTO(Categoria elemento) {
		this.id = elemento.getId();
		this.titulo = elemento.getTitulo();
		this.cor = elemento.getCor();
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public static List<CategoriaDTO> converter(Iterable<Categoria> lista) {
		List<CategoriaDTO> retornoLista = new ArrayList<>();
		lista.forEach( elemento -> retornoLista.add(new CategoriaDTO(elemento)));
		
		return retornoLista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
