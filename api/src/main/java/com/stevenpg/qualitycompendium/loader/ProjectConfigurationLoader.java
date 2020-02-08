package com.stevenpg.qualitycompendium.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stevenpg.qualitycompendium.config.ConfigProperties;
import com.stevenpg.qualitycompendium.data.StateSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class ProjectConfigurationLoader {

    private final ConfigProperties configProperties;

    @Autowired
    public ProjectConfigurationLoader(ConfigProperties configProperties){
        this.configProperties = configProperties;
    }

    @Bean
    @Scheduled(fixedRateString = "${qc.update-interval}")
    public void loadConfigurationFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StateSingleton singleton = StateSingleton.getInstance();

        var model = mapper.readValue(new File(configProperties.getFileLocation()), ProjectConfigurationFile.class);
        singleton.setProjectPages(model.getProjectPages());
        singleton.setSonarhosturl(model.getSonarhosturl());
        singleton.setBasicAuthHeader(model.base64Credentials());
    }



}
