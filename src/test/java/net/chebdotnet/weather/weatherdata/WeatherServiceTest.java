package net.chebdotnet.weather.weatherdata;

import net.chebdotnet.weather.geodata.GeoDocument;
import net.chebdotnet.weather.weatherdata.openweathermap.OpenWeatherMapService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static net.chebdotnet.weather.weatherdata.WeatherTestUtility.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.empty;

@ExtendWith(SpringExtension.class)
class WeatherServiceTest {

    @Mock
    private OpenWeatherMapService openWeatherMapService;

    @Mock
    private WeatherMapper mapper;

    @Mock(answer = RETURNS_DEEP_STUBS)
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherService service;

    @Test
    @DisplayName("Should get weather by geo data")
    void shouldGetWeatherByGeoData() {
        GeoDocument geoDocument = buildGeoDocument();

        when(openWeatherMapService.getWeatherByGeoData(buildGeoDocument())).thenReturn(buildOpenWeatherMapResponseMono());
        when(mapper.mapWeatherResponseToDocument(any())).thenReturn(buildWeatherDocument());
        when(weatherRepository.save(buildWeatherDocument())).thenReturn(buildWeatherDocumentMono());
        when(weatherRepository.findById(any(String.class))).thenReturn(empty());
        when(mapper.mapWeatherDocumentToWeatherDto(any())).thenReturn(buildWeatherDto());

        Mono<WeatherDto> weatherDtoMono = service.getWeatherByGeoData(geoDocument);

        StepVerifier
                .create(weatherDtoMono)
                .consumeNextWith(weatherDto -> {
                    assertEquals(new BigDecimal(MOCK_LAT), weatherDto.getCoord().getLat());
                    assertEquals(new BigDecimal(MOCK_LON), weatherDto.getCoord().getLon());
                    assertEquals(MOCK_TEMPERATURE, weatherDto.getMain().getTemp());
                    assertEquals(MOCK_BASE, weatherDto.getBase());
                })
                .verifyComplete();
    }
}
