package com.stevenpg.qualitycompendium.config;

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

    @Deprecated(since = "2.0", forRemoval = true)
    public static final List<String> configuredMeasuresGroupingOne =
            List.of(
                    "complexity",
                    "duplicated_blocks",
                    "violations",
                    "code_smells",
                    "sqale_rating",
                    "sqale_index",
                    "alert_status",
                    "bugs",
                    "reliability_rating",
                    "reliability_remediation_effort",
                    "vulnerabilities",
                    "security_remediation_effort",
                    "classes"
                    );

    @Deprecated(since = "2.0", forRemoval = true)
    public static final List<String> configuredMeasuresGroupingTwo =
            List.of(
                    "comment_lines",
                    "security_rating",
                    "lines",
                    "ncloc",
                    "coverage",
                    "new_coverage",
                    "uncovered_lines",
                    "tests",
                    "test_errors",
                    "test_failures",
                    "test_success_density"
            );
}
