package com.stevenpg.qualitycompendium.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stevenpg.qualitycompendium.data.StateSingleton;
import com.stevenpg.qualitycompendium.loader.ProjectPage;
import com.stevenpg.qualitycompendium.models.response.projectsearch.PagingData;
import com.stevenpg.qualitycompendium.models.response.projectsearch.ProjectSearchResponse;
import com.stevenpg.qualitycompendium.models.response.projectsearch.SonarComponent;
import com.stevenpg.qualitycompendium.service.SonarQubeDataProxyServicev2;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}