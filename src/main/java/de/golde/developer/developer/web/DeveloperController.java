package de.golde.developer.developer.web;

import de.golde.developer.developer.DeveloperService;
import de.golde.developer.developer.model.Developer;
import de.golde.developer.developer.model.DeveloperLanguageRank;
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
    public HttpEntity<List<Developer>> getDevelopers(@RequestParam String language) {
        return service.getDevelopersByLanguage(language)
                .collect(collectingAndThen(toList(), ResponseEntity::ok));
    }

    @GetMapping("languages/overview")
    public HttpEntity<List<DeveloperLanguageRank>> getDevelopersWithLanguageRanking() {
        return service.getDevelopersWithLanguageRanking()
                .collect(collectingAndThen(toList(), ResponseEntity::ok));
    }
}
