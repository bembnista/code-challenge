package de.golde.developer.developer.model;

import java.util.List;

public record DeveloperWithRepos(String name, List<Repository>repositories) { }
