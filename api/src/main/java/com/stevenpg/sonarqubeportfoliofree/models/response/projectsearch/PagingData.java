package com.stevenpg.sonarqubeportfoliofree.models.response.projectsearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PagingData {

    @JsonProperty("pageIndex")
    int pageIndex;

    @JsonProperty("pageSize")
    int pageSize;

    @JsonProperty("total")
    int total;

}
