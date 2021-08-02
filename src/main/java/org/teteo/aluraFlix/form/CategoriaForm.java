package org.teteo.aluraFlix.form;

import javax.validation.constraints.NotEmpty;

import org.teteo.aluraFlix.model.Categoria;

public class CategoriaForm {

	@NotEmpty
	private String titulo;
	
	@NotEmpty
	private String cor;
	
	public String getTitulo() {
		return titulo;
	}
	public String getCor() {
		return cor;
	}
	public Categoria converter() {
		return new Categoria(this);
	}
	
}
