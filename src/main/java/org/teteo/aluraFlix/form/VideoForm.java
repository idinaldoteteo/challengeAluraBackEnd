package org.teteo.aluraFlix.form;

import javax.validation.constraints.NotEmpty;

import org.teteo.aluraFlix.model.Video;

public class VideoForm {

	@NotEmpty
	private String titulo;
	
	@NotEmpty
	private String descricao;
	
	@NotEmpty
	private String url;
	
	private String categoriaId;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public Video converter() {
		return new Video(titulo, descricao, url, categoriaId);
	}
	public String getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}

	
}
