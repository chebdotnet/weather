package net.chebdotnet.weather.ip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static net.chebdotnet.weather.weatherdata.WeatherTestUtility.MOCK_IP_ADDRESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.just;

@ExtendWith(SpringExtension.class)
class LocalIpResolverServiceTest {

    @Mock
    AmazonAwsService amazonAwsService;

    @InjectMocks
    LocalIpResolverService service;

    @Test
    @DisplayName("Should resolve ip address on local workstation")
    void shouldResolveIpAddressOnLocalWorkstation() {
        when(amazonAwsService.getPublicIp()).thenReturn(just(MOCK_IP_ADDRESS));
        Mono<String> ipAddressMono = service.resolve();
        StepVerifier
                .create(ipAddressMono)
                .consumeNextWith(ipAddress -> assertEquals(ipAddress, MOCK_IP_ADDRESS))
                .verifyComplete();
    }
}
