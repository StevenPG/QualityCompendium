package com.stevenpg.qualitycompendium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties
public class QualityCompendiumApplication {

	public static void main(String[] args) {
		SpringApplication.run(QualityCompendiumApplication.class);
	}

}
