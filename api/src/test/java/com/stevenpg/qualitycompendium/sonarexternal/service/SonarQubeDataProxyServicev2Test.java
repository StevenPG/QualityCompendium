package com.stevenpg.qualitycompendium.sonarexternal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stevenpg.qualitycompendium.api.data.StateSingleton;
import com.stevenpg.qualitycompendium.api.datastore.ProjectPageRepository;
import com.stevenpg.qualitycompendium.api.loader.ProjectPage;
import com.stevenpg.qualitycompendium.sonarexternal.models.measures.Measure;
import com.stevenpg.qualitycompendium.sonarexternal.models.measures.MeasureBaseComponent;
import com.stevenpg.qualitycompendium.sonarexternal.models.measures.MeasuresResponse;
import com.stevenpg.qualitycompendium.sonarexternal.models.projectsearch.PagingData;
import com.stevenpg.qualitycompendium.sonarexternal.models.projectsearch.ProjectSearchResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
class SonarQubeDataProxyServicev2Test {

    public static MockWebServer mockSonarUrl;
    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    ProjectPageRepository repository;

    @InjectMocks
    private SonarQubeDataProxyServicev2 service = new SonarQubeDataProxyServicev2(repository);

    @BeforeEach
    void setUp() throws IOException {
        ArrayList<ProjectPage> projectPages = new ArrayList<>();

        ProjectPage samplePage = new ProjectPage();
        samplePage.setPagename("samplePageName");

        ArrayList projectKeys = new ArrayList<String>();
        projectKeys.add("sampleProjectKey");

        samplePage.setProjectKeys(projectKeys);

        projectPages.add(samplePage);

        mockSonarUrl = new MockWebServer();
        mockSonarUrl.start();
        StateSingleton.getInstance().setSonarhosturl(mockSonarUrl.getHostName() + ":" + mockSonarUrl.getPort());
        StateSingleton.getInstance().setBasicAuthHeader("BasicHeader");
        StateSingleton.getInstance().setProjectPages(projectPages);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockSonarUrl.shutdown();
    }

    @Test
    void getGroupMeasures() throws JsonProcessingException {
        var response = new MeasuresResponse();
        var pagingData = new PagingData();
        var baseComponent = new MeasureBaseComponent();
        var measure = new Measure();

        measure.setMetric("coverage");

        pagingData.setPageIndex(1);
        pagingData.setTotal(1);
        pagingData.setPageSize(1);

        baseComponent.setMeasures(Arrays.asList(measure));

        response.setPage(pagingData);
        response.setBaseComponent(baseComponent);

        String jsonString = mapper.writeValueAsString(response);

        mockSonarUrl.enqueue(new MockResponse()
                .setBody(jsonString)
                .addHeader("Content-Type", "application/json")
        );
        assertEquals(Arrays.asList(response).toString(), service.getGroupMeasures("samplePageName").collect(Collectors.toList()).block().toString());
    }

    @Test
    void getProjectMeasures() throws JsonProcessingException {
        var response = new MeasuresResponse();
        var pagingData = new PagingData();
        var baseComponent = new MeasureBaseComponent();
        var measure = new Measure();

        measure.setMetric("coverage");

        pagingData.setPageIndex(1);
        pagingData.setTotal(1);
        pagingData.setPageSize(1);

        baseComponent.setMeasures(Arrays.asList(measure));

        response.setPage(pagingData);
        response.setBaseComponent(baseComponent);

        String jsonString = mapper.writeValueAsString(response);

        mockSonarUrl.enqueue(new MockResponse()
                .setBody(jsonString)
                .addHeader("Content-Type", "application/json")
        );
        assertEquals(response.toString(), service.getProjectMeasures("sampleProjectKey").block().toString());
    }

    @Test
    void getSonarQubeStatus() {
        mockSonarUrl.enqueue(new MockResponse().setBody(mockSonarUrl.getHostName() + ":" + mockSonarUrl.getPort()));
        assertEquals(HttpStatus.OK, service.getSonarQubeStatus().block());
    }

    @Test
    void getSpecificProject() throws JsonProcessingException {
        // Build component output
        ProjectSearchResponse response = new ProjectSearchResponse();
        String jsonString = mapper.writeValueAsString(response);

        mockSonarUrl.enqueue(new MockResponse()
                .setBody(jsonString)
                .addHeader("Content-Type", "application/json")
        );
        assertEquals(response, service.getSpecificProject("sampleProjectKey").block());
    }

    @Test
    void getRequestedProjects() throws JsonProcessingException {
        // Build component output
        ProjectSearchResponse response = new ProjectSearchResponse();
        String jsonString = mapper.writeValueAsString(response);

        mockSonarUrl.enqueue(new MockResponse()
                .setBody(jsonString)
                .addHeader("Content-Type", "application/json")
        );
        assertEquals(response, service.getRequestedProjects().block());
    }
}