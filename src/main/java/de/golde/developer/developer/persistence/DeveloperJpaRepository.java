package de.golde.developer.developer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeveloperJpaRepository extends JpaRepository<DeveloperJpaEntity, UUID> {

}
