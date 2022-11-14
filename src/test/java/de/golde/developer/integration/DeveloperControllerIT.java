package de.golde.developer.integration;

import de.golde.developer.developer.persistence.DeveloperJpaEntity;
import de.golde.developer.repository.persistence.RepositoryJpaEntity;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeveloperControllerIT extends AbstractIT {

    @BeforeAll
    void init() {
        deleteAll();

        val devEinstein = new DeveloperJpaEntity(null, "Einstein");
        val devRiemann = new DeveloperJpaEntity(null, "Riemann");

        val devEinsteinId = developerRepository.save(devEinstein).id;
        val devRiemannId = developerRepository.save(devRiemann).id;

        val repos = List.of(
                new RepositoryJpaEntity(null, "repoEinsteinJava", "url", "Java", devEinsteinId),
                new RepositoryJpaEntity(null, "repoEinsteinScala", "url", "Scala", devEinsteinId),
                new RepositoryJpaEntity(null, "repoRiemannJava", "url", "Java", devRiemannId),
                new RepositoryJpaEntity(null, "repoRiemannNull", "url", null, devRiemannId)
        );

        repoRepository.saveAll(repos);
    }

    @Test
    void test_GET_scala_developers() {
        init();

        webTestClient.get().uri("/api/v1/developers?language=scala")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Einstein")
                .jsonPath("$[1].name").doesNotExist();

    }

    @Test
    void test_GET_developer_rankings() {
        init();

        webTestClient.get().uri("/api/v1/developers/language-rankings")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].developerName").isEqualTo("Einstein")
                .jsonPath("$[0].languageRank").exists()
                .jsonPath("$[1].developerName").isEqualTo("Riemann")
                .jsonPath("$[1].languageRank").exists()
                .jsonPath("$[2].developerName").doesNotExist();;

    }
}
