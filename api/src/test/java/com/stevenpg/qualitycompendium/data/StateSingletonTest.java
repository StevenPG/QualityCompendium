package com.stevenpg.qualitycompendium.data;

import com.stevenpg.qualitycompendium.loader.ProjectPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StateSingletonTest {

    private ArrayList<ProjectPage> projectPages;
    private StateSingleton singleton;

    @Test
    void getInstance() {
        assertNotNull(StateSingleton.getInstance());
    }

    @BeforeEach
    void setUp() {
        singleton = StateSingleton.getInstance();

        projectPages = new ArrayList<>();
        ProjectPage page = new ProjectPage();
        page.setPagename("samplePageName");
        page.setProjectKeys(new ArrayList<String>(Arrays.asList("projectkey:test:one", "projectkey:test:two", "projectkey:test:three")));
        projectPages.add(page);

        singleton.setProjectPages(projectPages);
    }

    @AfterEach
    void tearDown() {
        projectPages = null;
    }

    /**
     * Requires ProjectPages to be set
     */
    @Test
    void allProjectsAsCSV() {
        assertEquals("projectkey:test:one,projectkey:test:two,projectkey:test:three", singleton.allProjectsAsCSV());
    }

    @Test
    void projectsAsCSV() {
        assertEquals("", singleton.projectsAsCSV("sampleProjectPage"));
    }

}