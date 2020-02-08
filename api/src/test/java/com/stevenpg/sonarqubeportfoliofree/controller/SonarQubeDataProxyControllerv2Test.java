package com.stevenpg.sonarqubeportfoliofree.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stevenpg.sonarqubeportfoliofree.data.StateSingleton;
import com.stevenpg.sonarqubeportfoliofree.loader.ProjectPage;
import com.stevenpg.sonarqubeportfoliofree.models.response.measures.Measure;
import com.stevenpg.sonarqubeportfoliofree.models.response.measures.MeasureBaseComponent;
import com.stevenpg.sonarqubeportfoliofree.models.response.measures.MeasuresResponse;
import com.stevenpg.sonarqubeportfoliofree.models.response.projectsearch.PagingData;
import com.stevenpg.sonarqubeportfoliofree.models.response.projectsearch.ProjectSearchResponse;
import com.stevenpg.sonarqubeportfoliofree.models.response.projectsearch.SonarComponent;
import com.stevenpg.sonarqubeportfoliofree.service.SonarQubeDataProxyService;
import com.stevenpg.sonarqubeportfoliofree.service.SonarQubeDataProxyServicev2;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class SonarQubeDataProxyControllerv2Test {

    public static MockWebServer mockSonarUrl;
    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private SonarQubeDataProxyService dataService;

    @Mock
    private SonarQubeDataProxyServicev2 dataService2;

    @InjectMocks
    private SonarQubeDataProxyControllerv2 proxyController = new SonarQubeDataProxyControllerv2(dataService, dataService2);

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
}