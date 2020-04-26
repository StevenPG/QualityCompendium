package com.stevenpg.qualitycompendium.sonarexternal.models.measures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasureBaseComponent {
    private String id;
    private String key;
    private String name;
    private String description;
    private String qualifier;
    private List<Measure> measures;

}
