package com.stevenpg.qualitycompendium.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stevenpg.qualitycompendium.api.data.StateSingleton;
import com.stevenpg.qualitycompendium.api.loader.ProjectPage;
import com.stevenpg.qualitycompendium.sonarexternal.models.measures.MeasuresResponse;
import com.stevenpg.qualitycompendium.sonarexternal.models.projectsearch.PagingData;
import com.stevenpg.qualitycompendium.sonarexternal.models.projectsearch.ProjectSearchResponse;
import com.stevenpg.qualitycompendium.sonarexternal.models.projectsearch.SonarComponent;
import com.stevenpg.qualitycompendium.sonarexternal.service.SonarQubeDataProxyServicev2;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class SonarQubeDataProxyControllerv2Test {

    public static MockWebServer mockSonarUrl;
    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private SonarQubeDataProxyServicev2 dataService2;

    @InjectMocks
    private SonarQubeDataProxyControllerv2 proxyController = new SonarQubeDataProxyControllerv2(dataService2);

    // Data
    private ProjectSearchResponse projectSearchResponse;
    private ArrayList<SonarComponent> components;
    private PagingData pagingData;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        projectSearchResponse = new ProjectSearchResponse();

        pagingData = new PagingData();
        components = new ArrayList<>();
        components.add(new SonarComponent());
        projectSearchResponse.setComponentList(components);

        projectSearchResponse.setPage(pagingData);

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
    void tearDown() {
    }

    @Test
    void getProjectGroups() {
        assertEquals(Arrays.asList("samplePageName").toString(), proxyController.getProjectGroups().block().toString());
    }

    @Test
    void apiProxyGet() {
        ProjectSearchResponse projectSearchResponse = new ProjectSearchResponse();
        Mockito.when(dataService2.getRequestedProjects()).thenReturn(Mono.just(projectSearchResponse));
        assertEquals(projectSearchResponse, proxyController.apiProxyGet().block());
    }

    @Test
    void getProject() {
        ProjectSearchResponse projectSearchResponse = new ProjectSearchResponse();
        Mockito.when(dataService2.getSpecificProject(ArgumentMatchers.anyString())).thenReturn(Mono.just(projectSearchResponse));
        assertEquals(projectSearchResponse, proxyController.getProject("testString").block());
    }

    @Test
    void getMeasuresFromGroup() {
        MeasuresResponse measuresResponse = new MeasuresResponse();
        Mockito.when(dataService2.getGroupMeasures(ArgumentMatchers.anyString())).thenReturn(Flux.just(measuresResponse));
        assertEquals(measuresResponse, proxyController.getMeasuresFromGroup("testString").blockFirst());
    }

    @Test
    void getMeasuresFromProject() {
        MeasuresResponse measuresResponse = new MeasuresResponse();
        Mockito.when(dataService2.getProjectMeasures(ArgumentMatchers.anyString())).thenReturn(Mono.just(measuresResponse));
        assertEquals(measuresResponse, proxyController.getMeasuresFromProject("testString").block());
    }

    @Test
    void getProjectKeysFromProjectGroup() {
        ProjectPage testPage = new ProjectPage();
        testPage.setPagename("testName");
        testPage.setProjectKeys(new ArrayList<>(Arrays.asList("project1", "project2")));

        StateSingleton.getInstance().setProjectPages(new ArrayList<ProjectPage>(Arrays.asList(testPage)));
        assertEquals(testPage.getProjectKeys(), proxyController.getProjectKeysFromProjectGroup("testName").block());
    }

    @Test
    void checkSonarConnection() {
        HttpStatus status = HttpStatus.OK;
        Mockito.when(dataService2.getSonarQubeStatus()).thenReturn(Mono.just(status));
        assertEquals(HttpStatus.OK, proxyController.checkSonarConnection().block());
    }

    @Test
    void getSonarHost() {
        StateSingleton.getInstance().setSonarhosturl("testHostURL");
        assertEquals("testHostURL", proxyController.getSonarHost().getBody());
    }
}