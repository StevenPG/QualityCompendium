package com.stevenpg.qualitycompendium.api.controller;

import com.stevenpg.qualitycompendium.api.datastore.ProjectPageEntity;
import com.stevenpg.qualitycompendium.sonarexternal.models.measures.MeasuresResponse;
import com.stevenpg.qualitycompendium.sonarexternal.models.projectsearch.ProjectSearchResponse;
import com.stevenpg.qualitycompendium.sonarexternal.service.SonarQubeDataProxyServicev2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.stevenpg.qualitycompendium.api.data.StateSingleton.getInstance;

@Slf4j
@RestController
public class SonarQubeDataProxyControllerv2 {

    private SonarQubeDataProxyServicev2 proxyServicev2;

    @Autowired
    public SonarQubeDataProxyControllerv2(SonarQubeDataProxyServicev2 proxyServicev2) {
        this.proxyServicev2 = proxyServicev2;
    }

    /**
     * {
     * 	"pagename": "testpage",
     * 	"projectKeys": "project1"
     * }
     */
    @CrossOrigin({ "*" })
    @PostMapping("/api/v2/addconfig")
    public Mono<HttpStatus> addProjectPageConfig(@RequestBody ProjectPageEntity entity) {
        this.proxyServicev2.addProjectPage(entity);
        return Mono.just(HttpStatus.OK);
    }

    /**
     * {
     * 	"pagename": "testpage"
     * }
     */
    @CrossOrigin({ "*" })
    @PostMapping("/api/v2/deleteconfig")
    public Mono<HttpStatus> deleteProjectPageConfig(@RequestBody ProjectPageEntity entity) {
        log.info("Deleting " + entity.toString());
        this.proxyServicev2.deleteProjectPage(entity.getPagename());
        return Mono.just(HttpStatus.OK);
    }

    /**
     * This method just returns the JSON output from the service call
     * that makes a search for all projects configured in the configuration file.
     * @return passthru of projects/search API call to SonarQube
     */
    @CrossOrigin({ "*" })
    @GetMapping("/api/v2/searchProjects")
    public @ResponseBody
    Mono<ProjectSearchResponse> apiProxyGet() {
        return proxyServicev2.getRequestedProjects();
    }

    /**
     * This method just returns the JSON output from the service call
     * that makes a search for all projects configured in the configuration file.
     * @return passthru of projects/search/{projectKey} API call to SonarQube
     */
    @CrossOrigin({ "*" })
    @GetMapping("/api/v2/searchProjects/{projectkey}")
    public @ResponseBody Mono<ProjectSearchResponse> getProject(@PathVariable("projectkey") String projectKey) {
        return proxyServicev2.getSpecificProject(projectKey);
    }

    /**
     * @return group names in array of strings for UI to render and make subsequent calls
     */
    @CrossOrigin({ "*" })
    @GetMapping("/api/v2/projectGroups")
    public @ResponseBody Mono<List<String>> getProjectGroups() {
        List<ProjectPageEntity> configuredPages = proxyServicev2.getProjectPages();
        return Mono.just(configuredPages.stream().map(projectpage -> projectpage.getPagename()).collect(Collectors.toList()));
    }

    /**
     * Return measures for project supplied
     */
    @CrossOrigin({ "*" })
    @GetMapping("/api/v2/projectGroups/{projectGroup}/measures")
    public @ResponseBody
    Flux<MeasuresResponse> getMeasuresFromGroup(@PathVariable("projectGroup") String projectGroup) {
        return proxyServicev2.getGroupMeasures(projectGroup);
    }

    /**
     * Return measures for project supplied
     */
    @CrossOrigin({ "*" })
    @GetMapping("/api/v2/projectKeys/{projectKey}/measures")
    public @ResponseBody Mono<MeasuresResponse> getMeasuresFromProject(@PathVariable("projectKey") String projectKey) {
        return proxyServicev2.getProjectMeasures(projectKey);
    }

    /**
     * Get project keys as array for UI to render and make subsequent calls
     * @param projectGroupName name of group to pull
     * @return array of keys from specified project group parameter
     */
    @CrossOrigin({ "*" })
    @GetMapping("/api/v2/projectGroups/{projectGroupName}/projectKeys")
    public @ResponseBody Mono<List<String>> getProjectKeysFromProjectGroup(@PathVariable("projectGroupName") String projectGroupName) {
        return Mono.just(proxyServicev2.getProjectPages().stream()
                .filter(projectPage -> projectPage.getPagename().equals(projectGroupName))
                .map(pp -> Arrays.asList(pp.getProjectKeys().split(",")))
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList()));

    }

    /**
     * @return status code from hitting sonar's /about page
     */
    @CrossOrigin({ "*" })
    @GetMapping("/api/v2/sonar-connection")
    public Mono<HttpStatus> checkSonarConnection() {
        return proxyServicev2.getSonarQubeStatus();
    }

    /**
     * @return configured sonar host url within config file
     */
    @CrossOrigin({ "*" })
    @GetMapping("/api/v2/sonar-host")
    public ResponseEntity<String> getSonarHost() {
        return new ResponseEntity<>(getInstance().getSonarhosturl(), HttpStatus.OK);
    }

}
