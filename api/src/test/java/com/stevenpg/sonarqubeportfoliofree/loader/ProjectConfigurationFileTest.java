package com.stevenpg.sonarqubeportfoliofree.loader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectConfigurationFileTest {

    ProjectConfigurationFile file;

    @BeforeEach
    public void setUp() {
        file = new ProjectConfigurationFile();
        file.setSonarReadonlyUser("username-test");
        file.setSonarReadonlyPass("username-pass");
    }

    @AfterEach
    public void tearDown() {
        file = null;
    }

    @Test
    void base64Credentials() {
        assertEquals("dXNlcm5hbWUtdGVzdDp1c2VybmFtZS1wYXNz", file.base64Credentials());
    }
}