package com.stevenpg.qualitycompendium;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Profile("test")
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
class QualityCompendiumApplicationTests {

	@Test
	void contextLoads() throws IOException {
		assertNotNull(new QualityCompendiumApplication());
	}

}
