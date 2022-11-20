package net.chebdotnet.weather.ip;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class AmazonAwsClientConfiguration {

    private final AmazonAwsClientProperties amazonAwsClientProperties;

    @Bean
    WebClient amazonAwsClient() {
        return WebClient.builder()
                .baseUrl(amazonAwsClientProperties.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
