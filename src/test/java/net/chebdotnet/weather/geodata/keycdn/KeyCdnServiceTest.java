package net.chebdotnet.weather.geodata.keycdn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Function;

import static net.chebdotnet.weather.geodata.GeoDataTestUtility.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class KeyCdnServiceTest {

    @Mock
    private KeyCdnProperties keyCdnProperties;
    @Mock
    private WebClient keyCdnClient;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    @Mock
    private WebClient.ResponseSpec responseMock;

    @InjectMocks
    private KeyCdnService service;

    @Test
    @DisplayName("Should get geo data by ip")
    void shouldGetGeoDataByIp() {

        when(keyCdnProperties.getRetries()).thenReturn(3);
        when(keyCdnProperties.getBackOff()).thenReturn(500);
        when(keyCdnClient.get()).thenReturn(requestHeadersUriMock);
        when(requestHeadersUriMock.uri(any(Function.class))).thenReturn(requestHeadersMock);
        when(requestHeadersMock.header("User-Agent", "keycdn-tools:http://" + MOCK_IP_ADDRESS)).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
        when(responseMock.bodyToMono(KeyCdnResponse.class))
                .thenReturn(buildKeyCdnResponseMono());

        Mono<KeyCdnResponse> keyCdnResponseMono = service.getGeoDataByIp(MOCK_IP_ADDRESS);

        StepVerifier
                .create(keyCdnResponseMono)
                .consumeNextWith(keyCdnResponse -> {
                    assertEquals(MOCK_IP_ADDRESS, keyCdnResponse.getData().getGeo().getIp());
                    assertEquals(MOCK_LAT, keyCdnResponse.getData().getGeo().getLatitude());
                    assertEquals(MOCK_LON, keyCdnResponse.getData().getGeo().getLongitude());

                })
                .verifyComplete();
    }
}
