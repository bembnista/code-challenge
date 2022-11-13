package de.golde.developer.developer.persistence;

import de.golde.developer.developer.DeveloperAdapter;
import de.golde.developer.developer.model.DeveloperWithRepos;
import de.golde.developer.developer.model.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
class DeveloperJpaRepositoryAdapter implements DeveloperAdapter {

    private final DeveloperJpaRepository developerRepository;

    public DeveloperJpaRepositoryAdapter(DeveloperJpaRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Stream<DeveloperWithRepos> getDevelopersWithRepositories() {
        return developerRepository.findAllWithRepositories().stream().map(toDomainModel);
    }

    private final Function<DeveloperJpaEntity, List<Repository>> repositories = jpaEntity ->
            jpaEntity.getRepositories().stream().map(it -> new Repository("", it.url, it.language)).collect(toList());

    private final Function<DeveloperJpaEntity, DeveloperWithRepos> toDomainModel = jpaEntity ->
            new DeveloperWithRepos(
                    jpaEntity.name,
                    repositories.apply(jpaEntity)
            );

}
