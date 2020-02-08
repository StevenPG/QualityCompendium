package com.stevenpg.qualitycompendium.models.response.projectsearch;

import lombok.Data;

@Data
public class SonarComponent {

    String organization;
    String id;
    String key;
    String name;
    String qualifier;
    String visibility;
    String lastAnalysisDate;
    String revision;

}
