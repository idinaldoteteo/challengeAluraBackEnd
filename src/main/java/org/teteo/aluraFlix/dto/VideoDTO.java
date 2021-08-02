package org.teteo.aluraFlix.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.teteo.aluraFlix.model.Video;

public class VideoDTO {

	private Long id;
	private Long categoriaId;
	private String titulo;
	private String descricao;
	private String url;
	
	public VideoDTO(Video video) {
		this.id = video.getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
		if(  Objects.isNull(video.getCategoria())) {
			this.categoriaId = 1L;
		}else {
			this.categoriaId = video.getCategoria().getId();
		}
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUrl() {
		return url;
	}

	public static List<VideoDTO> converter(Iterable<Video> videos) {
		List<VideoDTO> videosList = new ArrayList<>();
		videos.forEach( video -> videosList.add(new VideoDTO(video)) );
		
		return videosList;
	}

	public static VideoDTO converter(Video video) {
		return new VideoDTO(video);
	}

	public Long getCategoriaId() {
		return categoriaId;
	}
	
}
