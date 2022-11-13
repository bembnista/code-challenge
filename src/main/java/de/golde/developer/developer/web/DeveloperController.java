package de.golde.developer.developer.web;

import de.golde.developer.developer.DeveloperService;
import de.golde.developer.developer.model.DeveloperWithRepos;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private final DeveloperService service;

    public DeveloperController(DeveloperService service) {
        this.service = service;
    }

    @GetMapping
    public HttpEntity<List<DeveloperWithRepos>> getDevelopers(@RequestParam(required = false) String language) {
        return service.getDevelopersWithRepositoriesByLanguage(language)
                .collect(collectingAndThen(toList(), ResponseEntity::ok));
    }
}
