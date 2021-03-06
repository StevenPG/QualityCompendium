package com.stevenpg.qualitycompendium.models.response.measures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stevenpg.qualitycompendium.models.response.projectsearch.PagingData;
import lombok.Data;

@Data
public class MeasuresResponse {
    @JsonProperty("paging")
    PagingData page;

    MeasureBaseComponent baseComponent;
}
