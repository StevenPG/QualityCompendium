package com.stevenpg.sonarqubeportfoliofree.service;

import com.stevenpg.sonarqubeportfoliofree.config.Measures;
import com.stevenpg.sonarqubeportfoliofree.data.StateSingleton;
import com.stevenpg.sonarqubeportfoliofree.models.response.measures.MeasureBaseComponent;
import com.stevenpg.sonarqubeportfoliofree.models.response.measures.MeasuresResponse;
import com.stevenpg.sonarqubeportfoliofree.models.response.projectsearch.ProjectSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@Deprecated(since = "2.0", forRemoval = true)
public class SonarQubeDataProxyService {

    private WebClient client = WebClient.create();
    private static final String BASIC_HEADER_PREFIX = "Basic ";
    private static final String BASIC_AUTH_HEADER_KEY = "Authorization";

    public Mono<ProjectSearchResponse> getRequestedProjects() {
        log.info("getRequestedProjects -> Retrieving configured project keys");
        return client.get()
                .uri(StateSingleton.getInstance().getSonarhosturl() + "/api/projects/search?projects=" + StateSingleton.getInstance().allProjectsAsCSV())
                .header(BASIC_AUTH_HEADER_KEY, BASIC_HEADER_PREFIX + StateSingleton.getInstance().getBasicAuthHeader())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProjectSearchResponse.class);
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

    /**
     * Sonar has a limitation of 15 measures per request, therefore we have to collate the requests together
     * into a single object
     * @param projectKey project key to retrieve measures from
     * @return single measures response object
     */
    @Deprecated(since = "2.0", forRemoval = true)
    public Mono<MeasuresResponse> getMeasuresForProject(String projectKey){
        String measuresPathOne = "/api/measures/component_tree?ps=500&component=" + projectKey + "&metricKeys=" + String.join(",", Measures.configuredMeasuresGroupingOne);
        String measuresPathTwo = "/api/measures/component_tree?ps=500&component=" + projectKey + "&metricKeys=" + String.join(",", Measures.configuredMeasuresGroupingTwo);
        MeasuresResponse responseOne = client.get()
                .uri(StateSingleton.getInstance().getSonarhosturl() + measuresPathOne)
                .header(BASIC_AUTH_HEADER_KEY, BASIC_HEADER_PREFIX + StateSingleton.getInstance().getBasicAuthHeader())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MeasuresResponse.class)
                .block();
        MeasuresResponse responseTwo = client.get()
                .uri(StateSingleton.getInstance().getSonarhosturl() + measuresPathTwo)
                .header(BASIC_AUTH_HEADER_KEY, BASIC_HEADER_PREFIX + StateSingleton.getInstance().getBasicAuthHeader())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MeasuresResponse.class)
                .block();

        // Retrieve responses and merge them together
        assert responseOne != null;
        assert responseTwo != null;
        var allMeasures = Stream.concat(
                responseOne.getBaseComponent().getMeasures().stream(),
                responseTwo.getBaseComponent().getMeasures().stream()
        ).collect(Collectors.toList());

        // Use allargs constructor to avoid extra method calls to modifiers
        var jointComponent = new MeasureBaseComponent(
                responseOne.getBaseComponent().getId(),
                responseOne.getBaseComponent().getKey(),
                responseOne.getBaseComponent().getName(),
                responseOne.getBaseComponent().getDescription(),
                responseOne.getBaseComponent().getQualifier(),
                allMeasures
        );

        var jointResponse = new MeasuresResponse();
        jointResponse.setPage(responseOne.getPage());
        jointResponse.setBaseComponent(jointComponent);

        log.info("getMeasuresForProject -> Retrieving Measures for " + projectKey);
        return Mono.just(jointResponse);
    }

}
