package org.teteo.aluraFlix.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.teteo.aluraFlix.dto.VideoDTO;
import org.teteo.aluraFlix.form.VideoForm;
import org.teteo.aluraFlix.model.Video;
import org.teteo.aluraFlix.repository.VideoRepository;

@RestController
@RequestMapping("/videos")
public class VideosController {
	
	@Autowired
	private VideoRepository videoRepository;

	@GetMapping
	public List<VideoDTO> getTodosVideos(){
		Iterable<Video> todosVideos = videoRepository.findAll();
		
		return VideoDTO.converter(todosVideos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VideoDTO> getVideo(@PathVariable Long id) {
		Optional<Video> video = videoRepository.findById(id);
		
		if(video.isPresent())
			return ResponseEntity.ok(VideoDTO.converter(video.get()));
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<VideoDTO> cadastrar(@RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder) {
		Video video = form.converter();
		videoRepository.save(video);
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VideoDTO(video));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid VideoForm form) {
		Optional<Video> optional = videoRepository.findById(id);
		
		if( optional.isPresent()) {
			Video video = form.atualizar(id, videoRepository);
			return ResponseEntity.ok(new VideoDTO(video));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<VideoDTO> deletar(@PathVariable Long id) {
		Optional<Video> optional = videoRepository.findById(id);
		
		if( optional.isPresent()) {
			videoRepository.deleteById(id);
			return ResponseEntity.ok(new VideoDTO(optional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
}
