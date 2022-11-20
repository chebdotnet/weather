package net.chebdotnet.weather.geodata;

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

import static net.chebdotnet.weather.geodata.GeoDataTestUtility.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = {MongoDbContainerInitializer.class})
class GeoRepositoryTest {

    @Autowired
    private GeoRepository repository;

    private static GeoDocument buildGeoDocument() {
        GeoDocument.Geo geo = new GeoDocument.Geo();
        geo.setIp(MOCK_IP_ADDRESS);
        geo.setLatitude(MOCK_LAT);
        geo.setLongitude(MOCK_LON);
        GeoDocument.GeoData geoData = new GeoDocument.GeoData();
        geoData.setGeo(geo);
        return GeoDocument.builder()
                .id(MOCK_IP_ADDRESS)
                .data(geoData)
                .build();
    }

    @Test
    @DisplayName("Should save geo document on database")
    public void shouldSaveGeoDocument() {
        GeoDocument geoDocument = buildGeoDocument();
        Publisher<GeoDocument> setup = repository.deleteAll().thenMany(repository.save(geoDocument));
        Mono<GeoDocument> find = repository.findById(MOCK_IP_ADDRESS);
        Publisher<GeoDocument> composite = Mono
                .from(setup)
                .then(find);

        StepVerifier
                .create(composite)
                .consumeNextWith(item -> {
                    assertNotNull(item.getData().getGeo().getIp());
                    assertEquals(item.getData().getGeo().getLatitude(), MOCK_LAT);
                    assertEquals(item.getData().getGeo().getLongitude(), MOCK_LON);
                })
                .verifyComplete();
    }
}
