package com.stevenpg.qualitycompendium.api.data;

import com.stevenpg.qualitycompendium.api.loader.ProjectPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StateSingletonTest {

    private ArrayList<ProjectPage> projectPages;
    private StateSingleton singleton;

    @Test
    void getInstance() {
        Assertions.assertNotNull(StateSingleton.getInstance());
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

}