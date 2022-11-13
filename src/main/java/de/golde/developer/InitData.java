package de.golde.developer;

import de.golde.developer.developer.DeveloperAdapter;
import de.golde.developer.github.GitHubAdapter;
import de.golde.developer.repository.RepositoryAdapter;
import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitData implements InitializingBean {

    private final GitHubAdapter gitHubAdapter;
    private final DeveloperAdapter developerAdapter;
    private final RepositoryAdapter repositoryAdapter;

    public InitData(GitHubAdapter gitHubAdapter, DeveloperAdapter developerAdapter, RepositoryAdapter repositoryAdapter) {
        this.gitHubAdapter = gitHubAdapter;
        this.developerAdapter = developerAdapter;
        this.repositoryAdapter = repositoryAdapter;
    }

    @Override
    public void afterPropertiesSet() {
        val developers = gitHubAdapter.getCodecentricMembers();

        developerAdapter.saveAllDevelopers(developers)
                .map(gitHubAdapter::getUserRepositories)
                .forEach(repositoryAdapter::saveDeveloperRepositories);
    }

}
