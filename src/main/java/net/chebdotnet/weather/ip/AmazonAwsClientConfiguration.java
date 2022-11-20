package net.chebdotnet.weather.ip;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@RequiredArgsConstructor
public class AmazonAwsClientConfiguration {

    private final AmazonAwsClientProperties amazonAwsClientProperties;

    @Bean
    WebClient amazonAwsClient() {
        return WebClient.builder()
                .baseUrl(amazonAwsClientProperties.getUrl())
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }
}
