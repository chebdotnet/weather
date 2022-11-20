package net.chebdotnet.weather.geodata;

import net.chebdotnet.weather.geodata.keycdn.KeyCdnService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static net.chebdotnet.weather.geodata.GeoDataTestUtility.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.empty;

@ExtendWith(SpringExtension.class)
class GeoDataServiceTest {

    @Mock
    private KeyCdnService keyCdnService;

    @Mock(answer = RETURNS_DEEP_STUBS)
    private GeoRepository geoRepository;

    @Mock
    private GeoMapper geoMapper;

    @InjectMocks
    private GeoDataService service;

    @Test
    @DisplayName("Should get geo data by ip address")
    void shouldGetGeoDataByIpAddress() {
        when(keyCdnService.getGeoDataByIp(MOCK_IP_ADDRESS)).thenReturn(buildKeyCdnResponseMono());
        when(geoMapper.mapGeoDataResponseToDocument(buildKeyCdnResponse())).thenReturn(buildGeoDocument());
        when(geoRepository.save(buildGeoDocument())).thenReturn(buildGeoDocumentMono());
        when(geoRepository.findById(any(String.class))).thenReturn(empty());

        Mono<GeoDocument> geoDocumentMono = service.getGeoDataByIpAddress(MOCK_IP_ADDRESS);

        StepVerifier
                .create(geoDocumentMono)
                .consumeNextWith(geoDocument -> {
                    assertEquals(MOCK_IP_ADDRESS, geoDocument.getData().getGeo().getIp());
                    assertEquals(MOCK_LAT, geoDocument.getData().getGeo().getLatitude());
                    assertEquals(MOCK_LON, geoDocument.getData().getGeo().getLongitude());
                })
                .verifyComplete();
    }
}
