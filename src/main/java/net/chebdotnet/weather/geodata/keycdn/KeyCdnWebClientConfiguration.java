package net.chebdotnet.weather.geodata.keycdn;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class KeyCdnWebClientConfiguration {
    private final KeyCdnProperties keyCdnProperties;

    @Bean
    WebClient keyCdnClient() {
        return WebClient.builder()
                .baseUrl(keyCdnProperties.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
