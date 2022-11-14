package de.golde.developer.developer.persistence;

import de.golde.developer.developer.DeveloperRepositoryAdapter;
import de.golde.developer.developer.model.Developer;
import de.golde.developer.developer.model.DeveloperLanguageRank;
import de.golde.developer.repository.persistence.RepositoryJpaEntity;
import de.golde.developer.repository.persistence.RepositoryJpaRepository;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Component
class DeveloperJpaRepositoryAdapter implements DeveloperRepositoryAdapter {

    private final DeveloperJpaRepository developerRepository;
    private RepositoryJpaRepository repositoryRepository;

    public DeveloperJpaRepositoryAdapter(DeveloperJpaRepository developerRepository, RepositoryJpaRepository repositoryRepository) {
        this.developerRepository = developerRepository;
        this.repositoryRepository = repositoryRepository;
    }

    @Override
    public Stream<Developer> getDevelopersByLanguage(final String language) {
        return repositoryRepository.findAllByLanguageIgnoreCase(language).stream()
                .map(RepositoryJpaEntity::getDeveloperId)
                .collect(collectingAndThen(toSet(), developerRepository::findAllById)).stream()
                .map(toDomainModel);
    }

    @Override
    public Stream<DeveloperLanguageRank> getDevelopersLanguageRanks() {
        return developerRepository.findAll().stream()
                .map(dev -> new DeveloperLanguageRank(dev.name, languageRanking.apply(dev.id)));
    }

    @Override
    public Stream<Developer> saveAllDevelopers(List<Developer> developers) {
        val savedDevelopers = developers.stream().map(toJpaEntity).collect(collectingAndThen(toList(), developerRepository::saveAll));
        return savedDevelopers.stream().map(toDomainModel);
    }

    private final Function<DeveloperJpaEntity, Developer> toDomainModel = jpaEntity -> new Developer(jpaEntity.id, jpaEntity.name);

    private final Function<Developer, DeveloperJpaEntity> toJpaEntity = domainModel -> new DeveloperJpaEntity(UUID.randomUUID(), domainModel.name());

    private final Function<UUID, Map<String, Integer>> languageRanking = developerId ->
            repositoryRepository.findAllByDeveloperIdAndLanguageNotNull(developerId).stream()
                    .collect(toMap(
                            repo -> repo.language,
                            repo -> 1,
                            (a, b) -> a + 1
                    ));
}
