package net.chebdotnet.weather.ip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.time.Duration.ofMillis;
import static reactor.util.retry.Retry.backoff;

@Service
@RequiredArgsConstructor
public class AmazonAwsService {

    private final AmazonAwsClientProperties amazonAwsClientProperties;
    private final WebClient amazonAwsClient;

    public Mono<String> getPublicIp() {
        return amazonAwsClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(backoff(amazonAwsClientProperties.getRetries(), ofMillis(amazonAwsClientProperties.getBackOff()))
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                            throw new AmazonAwsServiceException("External Service AmazonAwsService failed to process after max retries");
                        }));
    }

}
