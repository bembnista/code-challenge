package de.golde.developer.github;

import de.golde.developer.developer.model.Developer;
import de.golde.developer.developer.model.Repository;
import de.golde.developer.github.model.GitHubMember;
import de.golde.developer.github.model.GitHubRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class GitHubHttpClient extends AbstractHttpClient implements GitHubAdapter {

    private final Supplier<String> membersUrl = () -> "/orgs/codecentric/members";
    private final Function<String, String> reposByUserUrl = user -> "/users/" + user + "/repos";


    protected GitHubHttpClient(GitHubProperties githubProperties) {
        super(githubProperties);
    }


    @Override
    public List<Developer> getCodecentricMembers() {
        return client.get()
                .get().uri(membersUrl.get())
                .retrieve().bodyToFlux(GitHubMember.class)
                .map(toDeveloperModel)
                .doOnError(System.out::println)
                .onErrorComplete().collectList().block();
    }

    @Override
    public List<Repository> getUserRepositories(final Developer developer) {
        return client.get()
                .get().uri(reposByUserUrl.apply(developer.name()))
                .retrieve().bodyToFlux(GitHubRepository.class)
                .map(repository -> toRepositoryModel.apply(developer, repository))
                .doOnError(System.out::println)
                .collectList().block();
    }

    private final Function<GitHubMember, Developer> toDeveloperModel = gitHubMember -> new Developer(null, gitHubMember.login());

    private final BiFunction<Developer, GitHubRepository, Repository> toRepositoryModel =
            (developer, gitHubRepo) -> new Repository(developer, gitHubRepo.full_name(), gitHubRepo.url(), gitHubRepo.language());

}

