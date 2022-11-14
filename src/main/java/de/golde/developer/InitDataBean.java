package de.golde.developer;

import de.golde.developer.developer.DeveloperRepositoryAdapter;
import de.golde.developer.githubclient.GitHubAdapter;
import de.golde.developer.repository.RepoRepositoryAdapter;
import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitDataBean implements InitializingBean {

    private final GitHubAdapter gitHubAdapter;
    private final DeveloperRepositoryAdapter developerRepositoryAdapter;
    private final RepoRepositoryAdapter repositoryAdapter;

    public InitDataBean(GitHubAdapter gitHubAdapter, DeveloperRepositoryAdapter developerRepositoryAdapter, RepoRepositoryAdapter repositoryAdapter) {
        this.gitHubAdapter = gitHubAdapter;
        this.developerRepositoryAdapter = developerRepositoryAdapter;
        this.repositoryAdapter = repositoryAdapter;
    }

    @Override
    public void afterPropertiesSet() {
        val developers = gitHubAdapter.getCodecentricMembers();

        developerRepositoryAdapter.saveAllDevelopers(developers)
                .map(gitHubAdapter::getUserRepositories)
                .forEach(repositoryAdapter::saveDeveloperRepositories);
    }

}
