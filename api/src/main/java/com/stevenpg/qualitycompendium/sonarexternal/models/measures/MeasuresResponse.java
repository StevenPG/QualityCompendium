package com.stevenpg.qualitycompendium.sonarexternal.models.measures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stevenpg.qualitycompendium.sonarexternal.models.projectsearch.PagingData;
import lombok.Data;

@Data
public class MeasuresResponse {
    @JsonProperty("paging")
    PagingData page;

    MeasureBaseComponent baseComponent;
}
