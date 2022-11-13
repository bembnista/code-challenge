package de.golde.developer.developer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeveloperJpaRepository extends JpaRepository<DeveloperJpaEntity, UUID> {

    @Query(value = "SELECT dev FROM DeveloperJpaEntity dev LEFT JOIN RepositoryJpaEntity r ON dev = r.developer")
    List<DeveloperJpaEntity> findAllWithRepositories();
}
