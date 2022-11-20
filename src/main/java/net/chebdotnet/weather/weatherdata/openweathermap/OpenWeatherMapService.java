package net.chebdotnet.weather.weatherdata.openweathermap;

import lombok.RequiredArgsConstructor;
import net.chebdotnet.weather.geodata.GeoDocument;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.time.Duration.ofMillis;
import static reactor.util.retry.Retry.backoff;

@Service
@RequiredArgsConstructor
public class OpenWeatherMapService {

    private final WebClient openWeatherMapClient;
    private final OpenWeatherMapProperties openWeatherMapProperties;

    @Cacheable(value = "weatherByGeoData", key = "#geo.id")
    public Mono<OpenWeatherMapResponse> getWeatherByGeoData(GeoDocument geo) {
        return openWeatherMapClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .queryParam("lat", geo.getData().getGeo().getLatitude())
                                .queryParam("lon", geo.getData().getGeo().getLongitude())
                                .build()
                )
                .retrieve()
                .bodyToMono(OpenWeatherMapResponse.class)
                .retryWhen(backoff(openWeatherMapProperties.getRetries(), ofMillis(openWeatherMapProperties.getBackOff()))
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                            throw new OpenWeatherMapException("External Service OpenWeatherMapService failed to process after max retries");
                        }));
    }
}
