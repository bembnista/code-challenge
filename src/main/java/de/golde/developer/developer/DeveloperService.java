package de.golde.developer.developer;

import de.golde.developer.developer.model.DeveloperWithRepositories;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DeveloperService {

    private final DeveloperAdapter developerAdapter;

    public DeveloperService(DeveloperAdapter developerAdapter) {
        this.developerAdapter = developerAdapter;
    }

    public Stream<DeveloperWithRepositories> getDevelopersWithRepositoriesByLanguage(final String language) {
        return developerAdapter.getDevelopersWithRepositories();
    }
}
