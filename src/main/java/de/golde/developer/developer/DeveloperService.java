package de.golde.developer.developer;

import de.golde.developer.developer.model.Developer;
import de.golde.developer.developer.model.DeveloperLanguageRank;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DeveloperService {

    private final DeveloperRepositoryAdapter repositoryAdapter;

    public DeveloperService(DeveloperRepositoryAdapter repositoryAdapter) {
        this.repositoryAdapter = repositoryAdapter;
    }

    public Stream<Developer> getDevelopersByLanguage(final String language) {
        return repositoryAdapter.getDevelopersByLanguage(language);
    }

    public Stream<DeveloperLanguageRank> getDevelopersWithLanguageRanking() {
        return repositoryAdapter.getDevelopersLanguageRanks();
    }
}
