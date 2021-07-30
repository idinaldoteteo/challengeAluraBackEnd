package org.teteo.aluraFlix.repository;

import org.springframework.data.repository.CrudRepository;
import org.teteo.aluraFlix.model.Video;

public interface VideoRepository extends CrudRepository<Video, Long> {

}
