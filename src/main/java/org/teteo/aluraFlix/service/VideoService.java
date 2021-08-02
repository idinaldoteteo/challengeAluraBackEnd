package org.teteo.aluraFlix.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teteo.aluraFlix.dto.VideoDTO;
import org.teteo.aluraFlix.form.VideoForm;
import org.teteo.aluraFlix.model.Video;
import org.teteo.aluraFlix.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	private VideoRepository videoRepository;
	
	public List<VideoDTO> getTodosVideos() {
		Iterable<Video> todosVideos = videoRepository.findAll();
		
		return VideoDTO.converter(todosVideos);
	}

	public Optional<Video> getVideo(Long id) {
		return videoRepository.findById(id);
	}

	public VideoDTO cadastrar(@Valid VideoForm form) {
		Video video = form.converter();
		Video videoRetorno = videoRepository.save(video);
		return new VideoDTO(videoRetorno);
	}

	@Transactional
	public VideoDTO atualizar(Long id, @Valid VideoForm form) {
		Optional<Video> optional = videoRepository.findById(id);
		
		Video video = optional.get();
		video.setTitulo(form.getTitulo());
		video.setDescricao(form.getDescricao());
		video.setUrl(form.getUrl());
		
		return new VideoDTO(video);
	}

	@Transactional
	public void delete(Long id) {
		videoRepository.deleteById(id);
	}

	
}
