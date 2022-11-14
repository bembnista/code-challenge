package de.golde.developer.repository;

import de.golde.developer.developer.model.Repository;

import java.util.List;

public interface RepoRepositoryAdapter {
    void saveDeveloperRepositories(final List<Repository> repositories);
}
