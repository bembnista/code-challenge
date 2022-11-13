package de.golde.developer.developer.model;

import java.util.List;

public record DeveloperWithRepositories(Developer developer, List<Repository> repositories) { }
