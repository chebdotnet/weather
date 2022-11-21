package net.chebdotnet.weather.weatherdata;

import net.chebdotnet.weather.MongoDbContainerInitializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static net.chebdotnet.weather.weatherdata.WeatherTestUtility.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = {MongoDbContainerInitializer.class})
class WeatherRepositoryTest {

    @Autowired
    private WeatherRepository repository;

    @Test
    @DisplayName("Should save weather document on database")
    public void shouldSaveWeatherDocument() {
        WeatherDocument weatherDocument = buildWeatherDocument();
        Publisher<WeatherDocument> setup = repository.deleteAll().thenMany(repository.save(weatherDocument));
        Mono<WeatherDocument> find = repository.findById(MOCK_ID);
        Publisher<WeatherDocument> composite = Mono
                .from(setup)
                .then(find);

        StepVerifier
                .create(composite)
                .consumeNextWith(item -> {
                    assertNotNull(item.getId());
                    assertEquals(item.getName(), MOCK_NAME);
                    assertEquals(item.getBase(), MOCK_BASE);
                    assertEquals(item.getVisibility(), MOCK_VISIBILITY);
                    assertEquals(item.getCoord().getLat(), new BigDecimal(MOCK_LAT));
                    assertEquals(item.getCoord().getLon(), new BigDecimal(MOCK_LON));
                    assertEquals(item.getMain().getTemp(), MOCK_TEMPERATURE);
                    assertEquals(item.getClouds().getAll(), MOCK_ALL);
                    assertEquals(item.getWind().getSpeed(), MOCK_SPEED);
                    assertEquals(item.getSys().getCountry(), MOCK_COUNTRY);
                    assertEquals(item.getSys().getSunrise(), MOCK_SUNRISE);
                    assertEquals(item.getWeather().get(0).getMain(), MOCK_WEATHER_MAIN);
                    assertEquals(item.getWeather().get(0).getDescription(), MOCK_WEATHER_DESCRIPTION);
                })
                .verifyComplete();
    }

}
