package com.stevenpg.sonarqubeportfoliofree.controller;

import com.stevenpg.sonarqubeportfoliofree.data.StateSingleton;
import com.stevenpg.sonarqubeportfoliofree.models.response.measures.MeasuresResponse;
import com.stevenpg.sonarqubeportfoliofree.models.response.projectsearch.ProjectSearchResponse;
import com.stevenpg.sonarqubeportfoliofree.loader.ProjectPage;
import com.stevenpg.sonarqubeportfoliofree.service.SonarQubeDataProxyService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class SonarQubeDataProxyController {

    private SonarQubeDataProxyService dataService;

    @Autowired
    public SonarQubeDataProxyController(SonarQubeDataProxyService dataService) {
        this.dataService = dataService;
    }

    /**
     * This method just returns the JSON output from the service call
     * that makes a search for all projects configured in the configuration file.
     * @return passthru of projects/search API call to SonarQube
     */
    @CrossOrigin({ "*" })
    @GetMapping("/api/v1/searchProjects")
    @Deprecated(since = "2.0", forRemoval = true)
    public @ResponseBody
    Mono<ProjectSearchResponse> apiProxyGet() {
        return dataService.getRequestedProjects();
    }

    @CrossOrigin({ "*" })
    @GetMapping("/api/v1/searchProjects/{projectkey}")
    public @ResponseBody Mono<ProjectSearchResponse> getProject(@PathVariable("projectkey") String projectKey) {
        return dataService.getSpecificProject(projectKey);
    }

    @CrossOrigin({ "*" })
    @GetMapping("/api/v1/measures/{projectkey}")
    @Deprecated(since = "2.0", forRemoval = true)
    public @ResponseBody
    Mono<MeasuresResponse> getMeasures(@PathVariable("projectkey") String projectKey){
        return dataService.getMeasuresForProject(projectKey);
    }

    @CrossOrigin({ "*" })
    @GetMapping("/api/v1/projectPages")
    @Deprecated(since = "2.0", forRemoval = true)
    public @ResponseBody
    List<ProjectPage> getPages(){
        return StateSingleton.getInstance().getProjectPages();
    }

    @CrossOrigin({ "*" })
    @GetMapping("/api/v1/sonar-connection")
    @Deprecated(since = "2.0", forRemoval = true)
    public Mono<HttpStatus> checkSonarConnection() {
        return dataService.getSonarQubeStatus();
    }

    @CrossOrigin({ "*" })
    @GetMapping("/api/v1/sonar-host")
    @Deprecated(since = "2.0", forRemoval = true)
    public ResponseEntity<String> getSonarHost() {
        return new ResponseEntity<>(StateSingleton.getInstance().getSonarhosturl(), HttpStatus.OK);
    }

}
