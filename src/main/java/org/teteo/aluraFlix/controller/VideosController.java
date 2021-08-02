package org.teteo.aluraFlix.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.teteo.aluraFlix.dto.VideoDTO;
import org.teteo.aluraFlix.form.VideoForm;
import org.teteo.aluraFlix.model.Video;
import org.teteo.aluraFlix.service.VideoService;

@RestController
@RequestMapping("/videos")
public class VideosController {
	
	@Autowired
	private VideoService service;

	@GetMapping
	public List<VideoDTO> getTodosVideos(){
		return service.getTodosVideos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VideoDTO> getVideo(@PathVariable Long id) {
		Optional<Video> video = service.getVideo(id);
		
		if(video.isPresent())
			return ResponseEntity.ok(VideoDTO.converter(video.get()));
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<VideoDTO> cadastrar(@RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder) {
		VideoDTO videoDTO = service.cadastrar(form);
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(videoDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).body(videoDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VideoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid VideoForm form) {
		Optional<Video> video = service.getVideo(id);
		
		if( video.isPresent()) {
			VideoDTO videoDTO = service.atualizar(id, form);
			return ResponseEntity.ok(videoDTO);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<VideoDTO> deletar(@PathVariable Long id) {
		Optional<Video> video = service.getVideo(id);
		
		if( video.isPresent()) {
			service.delete(id);
			return ResponseEntity.ok(new VideoDTO(video.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
}
