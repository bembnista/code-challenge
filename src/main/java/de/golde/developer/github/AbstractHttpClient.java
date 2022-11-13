package de.golde.developer.github;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Supplier;

@Data
@Configuration
@ConfigurationProperties("client.git-hub")
class GitHubProperties {
    private String baseUrl;
}

public abstract class AbstractHttpClient {

    private GitHubProperties githubProperties;

    private final Supplier<String> baseUrl = () -> githubProperties.getBaseUrl();

    protected AbstractHttpClient(GitHubProperties githubProperties) {
        this.githubProperties = githubProperties;
    }

    protected Supplier<WebClient> client = () -> WebClient.builder().baseUrl(baseUrl.get()).build();

}
