package net.chebdotnet.weather.geodata;

import net.chebdotnet.weather.geodata.keycdn.KeyCdnResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static net.chebdotnet.weather.geodata.GeoDataTestUtility.buildKeyCdnResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class GeoMapperTest {

    private GeoMapper mapper = new GeoMapperImpl();

    @Test
    void shouldMapGeoDataResponseToDocument() {
        KeyCdnResponse keyCdnResponse = buildKeyCdnResponse();
        GeoDocument geoDocument = mapper.mapGeoDataResponseToDocument(keyCdnResponse);
        assertEquals(geoDocument.getData().getGeo().getLongitude(), keyCdnResponse.getData().getGeo().getLongitude());
    }
}
