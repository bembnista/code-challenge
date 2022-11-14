package de.golde.developer.developer;

import de.golde.developer.developer.model.Developer;
import de.golde.developer.developer.model.DeveloperLanguageRank;

import java.util.List;
import java.util.stream.Stream;

public interface DeveloperRepositoryAdapter {
    Stream<Developer> getDevelopersByLanguage(final String language);
    Stream<DeveloperLanguageRank> getDevelopersLanguageRanks();
    Stream<Developer> saveAllDevelopers(final List<Developer> developers);
}
