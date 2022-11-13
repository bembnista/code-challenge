package de.golde.developer.repository;

import de.golde.developer.developer.model.Repository;

import java.util.List;
import java.util.UUID;

public interface RepositoryAdapter {
    void saveDeveloperRepositories(final List<Repository> repositories);
}
