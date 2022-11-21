package net.chebdotnet.weather.weatherdata.openweathermap;

import net.chebdotnet.weather.geodata.GeoDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.function.Function;

import static net.chebdotnet.weather.weatherdata.WeatherTestUtility.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OpenWeatherMapServiceTest {

    @Mock(answer = RETURNS_DEEP_STUBS)
    private WebClient openWeatherMapClient;

    @Mock
    private OpenWeatherMapProperties openWeatherMapProperties;

    @InjectMocks
    private OpenWeatherMapService service;

    @Test
    @DisplayName("Should get weather by geo data")
    void shouldGetWeatherByGeoData() {
        GeoDocument geoDocument = buildGeoDocument();

        when(openWeatherMapProperties.getRetries()).thenReturn(3);
        when(openWeatherMapProperties.getBackOff()).thenReturn(500);
        when(openWeatherMapClient.get()
                .uri(any(Function.class))
                .retrieve()
                .bodyToMono(OpenWeatherMapResponse.class)
        ).thenReturn(buildOpenWeatherMapResponseMono());

        Mono<OpenWeatherMapResponse> weatherDataMono = service.getWeatherByGeoData(geoDocument);

        StepVerifier
                .create(weatherDataMono)
                .consumeNextWith(weatherData -> {
                    assertEquals(new BigDecimal(MOCK_LAT), weatherData.getCoord().getLat());
                    assertEquals(new BigDecimal(MOCK_LON), weatherData.getCoord().getLon());
                })
                .verifyComplete();
    }
}
