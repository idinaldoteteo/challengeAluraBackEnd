package org.teteo.aluraFlix.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teteo.aluraFlix.dto.CategoriaDTO;
import org.teteo.aluraFlix.dto.VideoDTO;
import org.teteo.aluraFlix.form.CategoriaForm;
import org.teteo.aluraFlix.model.Categoria;
import org.teteo.aluraFlix.model.Video;
import org.teteo.aluraFlix.repository.CategoriaRepository;
import org.teteo.aluraFlix.repository.VideoRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	public List<CategoriaDTO> getTodasCategorias() {
		Iterable<Categoria> lista = categoriaRepository.findAll();
		
		return CategoriaDTO.converter(lista);
	}

	public Optional<Categoria> getCategoria(Long id) {
		return categoriaRepository.findById(id);
	}

	public CategoriaDTO cadastrar(@Valid CategoriaForm form) {
		Categoria retornoCategoria = categoriaRepository.save(form.converter());
		
		return new CategoriaDTO(retornoCategoria);
	}

	@Transactional
	public CategoriaDTO atualizar(Long id, @Valid CategoriaForm form) {
		Categoria categoria = categoriaRepository.findById(id).get();
		categoria.setCor(form.getCor());
		categoria.setTitulo(form.getTitulo());
		
		return new CategoriaDTO(categoria);
	}

	@Transactional
	public void deletar(Long id) {
		categoriaRepository.deleteById(id);
	}

	public List<VideoDTO> getVideosPorCategoria(Long id) {
		Iterable<Video> todosVideos = videoRepository.findByCategoriaId(id);
		
		return VideoDTO.converter(todosVideos);
	}

}
