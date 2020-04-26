package com.stevenpg.qualitycompendium.api.config;

import java.util.List;

public class Measures {

    private Measures() {
        throw new InstantiationError();
    }

    public static final List<String> v2GroupMeasures =
            List.of(
                    "reliability_rating",
                    "security_rating",
                    "sqale_rating"
            );

    public static final List<String> v2ProjectMeasures =
            List.of(
                    "coverage",
                    "reliability_rating",
                    "security_rating",
                    "code_smells",
                    "sqale_rating",
                    "alert_status"
            );
}
