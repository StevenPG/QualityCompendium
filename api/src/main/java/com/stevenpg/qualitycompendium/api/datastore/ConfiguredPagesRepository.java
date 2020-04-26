package com.stevenpg.qualitycompendium.api.datastore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguredPagesRepository extends JpaRepository<ProjectPageEntity, Long> {

}
