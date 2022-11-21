package net.chebdotnet.weather.ip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CACHE_CONTROL;

@ExtendWith(SpringExtension.class)
class AmazonAwsServiceTest {

    private static final String MOCK_IP_ADDRESS = "5.199.143.67";

    @Mock(answer = RETURNS_DEEP_STUBS)
    private WebClient amazonAwsClient;

    @Mock
    private AmazonAwsClientProperties amazonAwsClientProperties;

    @InjectMocks
    private AmazonAwsService service;

    @Test
    @DisplayName("Should get public ip")
    void shouldGetPublicIp() {
        when(amazonAwsClientProperties.getRetries()).thenReturn(3);
        when(amazonAwsClientProperties.getBackOff()).thenReturn(500);

        when(amazonAwsClient.get()
                .header(CACHE_CONTROL, "no-cache")
                .retrieve()
                .bodyToMono(String.class)
        ).thenReturn(Mono.just(MOCK_IP_ADDRESS));

        Mono<String> ipAddressMono = service.getPublicIp();

        StepVerifier
                .create(ipAddressMono)
                .consumeNextWith(ipAddress -> assertEquals(MOCK_IP_ADDRESS, ipAddress))
                .verifyComplete();
    }
}
