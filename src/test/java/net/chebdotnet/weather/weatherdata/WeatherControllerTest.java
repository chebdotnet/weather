package net.chebdotnet.weather.weatherdata;

import net.chebdotnet.weather.MongoDbContainerInitializer;
import net.chebdotnet.weather.geodata.GeoDataService;
import net.chebdotnet.weather.ip.IpResolverService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.math.BigDecimal;

import static net.chebdotnet.weather.weatherdata.WeatherTestUtility.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = WeatherController.class)
@AutoConfigureDataMongo
@ContextConfiguration(initializers = {MongoDbContainerInitializer.class})
public class WeatherControllerTest {

    @MockBean
    private GeoDataService geoDataService;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private IpResolverService ipResolverService;

    @Autowired
    private WebTestClient webClient;

    @Test
    @DisplayName("Should get weather data transfer object by ip address")
    void shouldGetWeatherDtoByIp() throws IOException {
        when(ipResolverService.resolve()).thenReturn(Mono.just(MOCK_IP_ADDRESS));
        when(geoDataService.getGeoDataByIpAddress(MOCK_IP_ADDRESS)).thenReturn(buildGeoDocumentMono());
        when(weatherService.getWeatherByGeoData(buildGeoDocument())).thenReturn(buildWeatherDtoMono());
        webClient.get()
                .uri("/weather")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBody(WeatherDto.class)
                .value(WeatherDto::getBase, equalTo(MOCK_BASE))
                .value(WeatherDto::getVisibility, equalTo(MOCK_VISIBILITY))
                .value(weatherDto -> weatherDto.getCoord().getLat(), equalTo(new BigDecimal(MOCK_LAT)))
                .value(weatherDto -> weatherDto.getCoord().getLon(), equalTo(new BigDecimal(MOCK_LON)))
                .value(weatherDto -> weatherDto.getMain().getTemp(), equalTo(MOCK_TEMPERATURE))
                .value(weatherDto -> weatherDto.getClouds().getAll(), equalTo(MOCK_ALL))
                .value(weatherDto -> weatherDto.getWind().getSpeed(), equalTo(MOCK_SPEED))
                .value(weatherDto -> weatherDto.getSys().getCountry(), equalTo(MOCK_COUNTRY))
                .value(weatherDto -> weatherDto.getSys().getSunrise(), equalTo(MOCK_SUNRISE))
                .value(weatherDto -> weatherDto.getWeather().get(0).getMain(), equalTo(MOCK_WEATHER_MAIN))
                .value(weatherDto -> weatherDto.getWeather().get(0).getDescription(), equalTo(MOCK_WEATHER_DESCRIPTION));
    }

}
