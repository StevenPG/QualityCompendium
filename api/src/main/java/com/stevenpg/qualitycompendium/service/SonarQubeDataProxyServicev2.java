package com.stevenpg.qualitycompendium.service;

import com.stevenpg.qualitycompendium.config.Measures;
import com.stevenpg.qualitycompendium.data.StateSingleton;
import com.stevenpg.qualitycompendium.loader.ProjectPage;
import com.stevenpg.qualitycompendium.models.response.measures.MeasuresResponse;
import com.stevenpg.qualitycompendium.models.response.projectsearch.ProjectSearchResponse;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class SonarQubeDataProxyServicev2 {

    private WebClient client = WebClient.create();
    private static final String BASIC_HEADER_PREFIX = "Basic ";
    private static final String BASIC_AUTH_HEADER_KEY = "Authorization";

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
        ArrayList<String> projects = StateSingleton.getInstance().getProjectPages()
                .stream()
                .filter(projectPage -> projectPage.getPagename().equals(groupName))
                .findFirst()
                .map(ProjectPage::getProjectKeys)
                .get();

        return Flux.fromIterable(projects)
                .parallel()
                .runOn(Schedulers.elastic())
                .flatMap(this::getProjectMeasures)
                .sequential();
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

}
