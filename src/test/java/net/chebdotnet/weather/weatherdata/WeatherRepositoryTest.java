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
                })
                .verifyComplete();
    }

}
