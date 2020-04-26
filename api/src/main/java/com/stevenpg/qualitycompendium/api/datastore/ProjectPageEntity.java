package com.stevenpg.qualitycompendium.api.datastore;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProjectPageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String pagename;
    private String projectKeys;

}
