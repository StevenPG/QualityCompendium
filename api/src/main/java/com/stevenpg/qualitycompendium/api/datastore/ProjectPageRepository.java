package com.stevenpg.qualitycompendium.api.datastore;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectPageRepository extends CrudRepository<ProjectPageEntity, Long> {
    List<ProjectPageEntity> findByPagename(String pagename);
    ProjectPageEntity findById(long id);
    List<ProjectPageEntity> findAll();
}
