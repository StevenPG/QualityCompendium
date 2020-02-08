package com.stevenpg.sonarqubeportfoliofree.config;

import com.stevenpg.sonarqubeportfoliofree.models.response.measures.Measure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeasuresTest {

    @Test
    public void instantiateMeasures() {
        assertNotNull(Measures.configuredMeasuresGroupingOne);
        assertNotNull(Measures.configuredMeasuresGroupingTwo);
    }

}