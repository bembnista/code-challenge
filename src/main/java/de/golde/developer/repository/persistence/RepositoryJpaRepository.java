package de.golde.developer.repository.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryJpaRepository extends JpaRepository<RepositoryJpaEntity, UUID> {
}
