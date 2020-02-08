package com.stevenpg.qualitycompendium.models.response.projectsearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

/**
 * Sample JSON
 * {
 *     "paging" : {
 *          "pageIndex" : 1,
 *          "pageSize" : 1,
 *          "total" : 1
 *     },
 *     "components : [
 *          {
 *              "organization" : "",
 *              "id" : "",
 *              "key: """
 *              "name: """
 *              "qualifier": "",
 *              "visibility": "",
 *              "lastAnalysisDate": "today",
 *              "revision" : "1",
 *          }
 *     ]
 * }
 */
@Data
public class ProjectSearchResponse {

    @JsonProperty("paging")
    PagingData page;

    @JsonProperty("components")
    ArrayList<SonarComponent> componentList;

}
