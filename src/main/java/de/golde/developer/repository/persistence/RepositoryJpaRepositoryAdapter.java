package de.golde.developer.repository.persistence;

import de.golde.developer.developer.model.Repository;
import de.golde.developer.repository.RepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Component
public class RepositoryJpaRepositoryAdapter implements RepositoryAdapter {

    private final RepositoryJpaRepository repositoryJpaRepository;

    public RepositoryJpaRepositoryAdapter(RepositoryJpaRepository repositoryJpaRepository) {
        this.repositoryJpaRepository = repositoryJpaRepository;
    }

    @Override
    public void saveDeveloperRepositories(final List<Repository> repositories) {
        repositories.stream()
                .map(repo -> new RepositoryJpaEntity(UUID.randomUUID(), repo.name(), repo.url(), repo.language(), repo.developer().id()))
                .collect(collectingAndThen(toList(), repositoryJpaRepository::saveAll));
    }


}
