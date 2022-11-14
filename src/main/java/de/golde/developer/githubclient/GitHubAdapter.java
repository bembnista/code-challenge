package de.golde.developer.githubclient;

import de.golde.developer.developer.model.Developer;
import de.golde.developer.developer.model.Repository;

import java.util.List;

public interface GitHubAdapter {
    List<Developer> getCodecentricMembers();
    List<Repository> getUserRepositories(final Developer user);
}
