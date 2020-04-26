package com.stevenpg.qualitycompendium.sonarexternal.service;

import com.stevenpg.qualitycompendium.api.config.Measures;
import com.stevenpg.qualitycompendium.api.data.StateSingleton;
import com.stevenpg.qualitycompendium.api.datastore.ProjectPageEntity;
import com.stevenpg.qualitycompendium.api.datastore.ProjectPageRepository;
import com.stevenpg.qualitycompendium.api.loader.ProjectPage;
import com.stevenpg.qualitycompendium.sonarexternal.models.measures.MeasuresResponse;
import com.stevenpg.qualitycompendium.sonarexternal.models.projectsearch.ProjectSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SonarQubeDataProxyServicev2 {

    private WebClient client = WebClient.create();
    private static final String BASIC_HEADER_PREFIX = "Basic ";
    private static final String BASIC_AUTH_HEADER_KEY = "Authorization";
    private ProjectPageRepository repository;

    @Autowired
    public SonarQubeDataProxyServicev2(ProjectPageRepository repository){
        this.repository = repository;
        // temporarily bootstrap
        var item1 = new ProjectPageEntity();
        item1.setPagename("All Projects");
        item1.setProjectKeys("com.stevenpg:SonarQube-Portfolio-Free");
        var item2 = new ProjectPageEntity();
        item2.setPagename("Realistic Project");
        item2.setProjectKeys("project1,com.stevenpg:SonarQube-Portfolio-Free");
        repository.save(item1);
        repository.save(item2);
    }

    /**
     * Retrieve all project pages out of the database
     * @return
     */
    public List<ProjectPageEntity> getProjectPages() {
        return repository.findAll();
    }

    /**
     * Retrieve all projects as CSV out of the database
     * @return
     */
    public String allProjectsAsCSV(){
        return getProjectPages().stream()
                .flatMap(projectPage -> Arrays.asList(projectPage.getProjectKeys().split(",")).stream())
                .collect(Collectors.joining(","));
    }

    /**
     * Retrieve specific project as CSV out of the database
     * @return
     */
    public String projectsAsCSV(String projectPage) {
        return  getProjectPages().stream()
                .filter(page -> projectPage.equals(page.getPagename()))
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    /**
     * Get overall group measures by iterating through keys and retrieving relevant metrics, before averaging for return
     *
     * Supports the following measures:
     * - Releasability
     * - Reliability
     * - Security
     * - Maintainability
     *
     * @return primary measures as obj for UI
     */
    public Flux<MeasuresResponse> getGroupMeasures(String groupName) {
        List<String> projects = getProjectPages()
                .stream()
                .filter(projectPage -> projectPage.getPagename().contains(groupName))
                .findFirst()
                .map(projectPage -> Arrays.asList(projectPage.getProjectKeys().split(",")))
                .get();

        return Flux.fromIterable(projects)
                .parallel()
                .runOn(Schedulers.elastic())
                .flatMap(this::getProjectMeasures)
                .sequential();
    }

    /**
     * Return project data based on input parameter
     * @param projectKey incoming key for project
     * @return project data in form of search response
     */
    public Mono<ProjectSearchResponse> getSpecificProject(String projectKey) {
        log.info("getSpecificProject -> Retrieving project data for " + projectKey);
        return client.get()
                .uri(StateSingleton.getInstance().getSonarhosturl() + "/api/projects/search?projects=" + projectKey)
                .header(BASIC_AUTH_HEADER_KEY, BASIC_HEADER_PREFIX + StateSingleton.getInstance().getBasicAuthHeader())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProjectSearchResponse.class)
                .timeout(Duration.ofSeconds(5));
    }

    /**
     * Get projects configured
     * @return requested projects
     */
    public Mono<ProjectSearchResponse> getRequestedProjects() {
        log.info("getRequestedProjects -> Retrieving configured project keys");
        return client.get()
                .uri(StateSingleton.getInstance().getSonarhosturl() + "/api/projects/search?projects=" + allProjectsAsCSV())
                .header(BASIC_AUTH_HEADER_KEY, BASIC_HEADER_PREFIX + StateSingleton.getInstance().getBasicAuthHeader())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProjectSearchResponse.class);
    }

    /**
     * Get primary measures from project
     *
     * @return primary measures for project
     */
    public Mono<MeasuresResponse> getProjectMeasures(String projectKey) {
        return client.get()
                .uri(
                        new StringBuilder()
                                .append(StateSingleton.getInstance().getSonarhosturl())
                                .append("/api/measures/component_tree?ps=500&component=")
                                .append(projectKey)
                                .append("&metricKeys=")
                                .append(String.join(",", Measures.v2ProjectMeasures))
                                .toString()
                )
                .header(BASIC_AUTH_HEADER_KEY, BASIC_HEADER_PREFIX + StateSingleton.getInstance().getBasicAuthHeader())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MeasuresResponse.class);
    }

    public Mono<HttpStatus> getSonarQubeStatus() {
        return client.get()
                .uri(StateSingleton.getInstance().getSonarhosturl() + "/about")
                .exchange()
                .map(ClientResponse::statusCode).subscribeOn(Schedulers.elastic())
                .timeout(Duration.ofSeconds(5));
    }

}
