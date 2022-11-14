package de.golde.developer.integration;

import de.golde.developer.DeveloperApplication;
import de.golde.developer.developer.persistence.DeveloperJpaRepository;
import de.golde.developer.repository.persistence.RepositoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("test")
@SpringBootTest(classes = {DeveloperApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public abstract class AbstractIT {

    @Autowired
    protected WebTestClient webTestClient;
    @Autowired
    protected DeveloperJpaRepository developerRepository;
    @Autowired
    protected RepositoryJpaRepository repoRepository;

    protected void deleteAll() {
        repoRepository.deleteAll();
        developerRepository.deleteAll();
    }
}
