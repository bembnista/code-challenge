package de.golde.developer.developer;

import de.golde.developer.developer.model.DeveloperWithRepos;

import java.util.stream.Stream;

public interface DeveloperAdapter {
    Stream<DeveloperWithRepos> getDevelopersWithRepositories();
}
