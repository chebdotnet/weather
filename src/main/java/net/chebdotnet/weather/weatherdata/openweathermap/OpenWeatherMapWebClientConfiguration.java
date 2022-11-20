package net.chebdotnet.weather.weatherdata.openweathermap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@RequiredArgsConstructor
public class OpenWeatherMapWebClientConfiguration {

    private static final String API_KEY_QUERY_PARAMETER = "appid";
    private final OpenWeatherMapProperties openWeatherMapProperties;

    @Bean
    WebClient openWeatherMapClient() {
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(uriComponentsBuilder());
        return WebClient.builder()
                .uriBuilderFactory(defaultUriBuilderFactory)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private UriComponentsBuilder uriComponentsBuilder() {
        return UriComponentsBuilder.fromHttpUrl(openWeatherMapProperties.getUrl())
                .queryParam(API_KEY_QUERY_PARAMETER, openWeatherMapProperties.getApiKey());
    }
}
