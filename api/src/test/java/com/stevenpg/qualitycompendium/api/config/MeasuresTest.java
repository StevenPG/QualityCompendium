package com.stevenpg.qualitycompendium.api.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeasuresTest {

    @Test
    public void instantiateMeasures() {
        assertNotNull(Measures.v2ProjectMeasures);
        assertNotNull(Measures.v2GroupMeasures);
    }

}