package net.chebdotnet.weather.geodata.keycdn;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.time.Duration.ofMillis;
import static reactor.util.retry.Retry.backoff;

@Service
@RequiredArgsConstructor
public class KeyCdnService {

    private final KeyCdnProperties keyCdnProperties;
    private final WebClient keyCdnClient;

    @Cacheable("geoData")
    public Mono<KeyCdnResponse> getGeoDataByIp(String ipAddress) {
        return keyCdnClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("host", ipAddress)
                        .build())
                .header("User-Agent", "keycdn-tools:http://" + ipAddress)
                .retrieve()
                .bodyToMono(KeyCdnResponse.class)
                .retryWhen(backoff(keyCdnProperties.getRetries(), ofMillis(keyCdnProperties.getBackOff()))
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                            throw new KeyCdnServiceException("External Service KeyCdnService failed to process after max retries");
                        }));
    }
}
