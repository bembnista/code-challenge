package de.golde.developer.repository.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositoryJpaRepository extends JpaRepository<RepositoryJpaEntity, UUID> {
    List<RepositoryJpaEntity> findAllByLanguageIgnoreCase(String language);
    List<RepositoryJpaEntity> findAllByDeveloperIdAndLanguageNotNull(UUID developerId);
}
