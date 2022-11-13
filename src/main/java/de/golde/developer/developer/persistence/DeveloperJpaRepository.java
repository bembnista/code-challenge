package de.golde.developer.developer.persistence;

import de.golde.developer.developer.model.DeveloperWithRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface DeveloperJpaRepository extends JpaRepository<DeveloperJpaEntity, UUID> {

}
