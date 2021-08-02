package org.teteo.aluraFlix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.teteo.aluraFlix.model.Video;

public interface VideoRepository extends CrudRepository<Video, Long> {

	@Query("select v from Video v where v.categoria.id = :id")
	List<Video> findByCategoriaId(@Param("id") Long id);

	@Query("select v from Video v where v.titulo like %:search%")
	Iterable<Video> searchByVideoLike(@Param("search") String search);

}
