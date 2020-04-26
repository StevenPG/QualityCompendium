package com.stevenpg.qualitycompendium.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "qc")
public class ConfigProperties {
    @Value("${qc.file-location}")
    String fileLocation;

    @Value("${qc.update-interval}")
    String updateInterval;
}
