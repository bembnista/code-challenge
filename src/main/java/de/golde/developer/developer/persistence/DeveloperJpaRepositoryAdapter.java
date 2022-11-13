package de.golde.developer.developer.persistence;

import de.golde.developer.developer.DeveloperAdapter;
import de.golde.developer.developer.model.Developer;
import de.golde.developer.developer.model.DeveloperWithRepositories;
import de.golde.developer.developer.model.Repository;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Component
class DeveloperJpaRepositoryAdapter implements DeveloperAdapter {

    private final DeveloperJpaRepository developerRepository;

    public DeveloperJpaRepositoryAdapter(DeveloperJpaRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Stream<DeveloperWithRepositories> getDevelopersWithRepositories() {
        return Stream.of();
    }

    @Override
    public Stream<Developer> saveAllDevelopers(List<Developer> developers) {
        val savedDevelopers = developers.stream().map(toJpaEntity).collect(collectingAndThen(toList(), developerRepository::saveAll));
        return savedDevelopers.stream().map(toDDeveloperModel);
    }

    private final Function<DeveloperJpaEntity, Developer> toDDeveloperModel = jpaEntity -> new Developer(jpaEntity.id, jpaEntity.name);

    private final Function<Developer, DeveloperJpaEntity> toJpaEntity = domainModel -> new DeveloperJpaEntity(UUID.randomUUID(), domainModel.name());

}
