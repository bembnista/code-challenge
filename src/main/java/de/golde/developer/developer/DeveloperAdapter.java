package de.golde.developer.developer;

import de.golde.developer.developer.model.Developer;
import de.golde.developer.developer.model.DeveloperWithRepositories;

import java.util.List;
import java.util.stream.Stream;

public interface DeveloperAdapter {
    Stream<DeveloperWithRepositories> getDevelopersWithRepositories();
    Stream<Developer> saveAllDevelopers(final List<Developer> developers);
}
