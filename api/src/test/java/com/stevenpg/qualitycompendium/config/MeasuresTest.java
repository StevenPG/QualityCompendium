package com.stevenpg.qualitycompendium.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeasuresTest {

    @Test
    public void instantiateMeasures() {
        assertNotNull(Measures.v2ProjectMeasures);
        assertNotNull(Measures.v2GroupMeasures);
    }

}