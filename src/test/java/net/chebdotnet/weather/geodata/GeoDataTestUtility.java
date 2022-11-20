package net.chebdotnet.weather.geodata;

import net.chebdotnet.weather.geodata.keycdn.KeyCdnResponse;
import reactor.core.publisher.Mono;

public class GeoDataTestUtility {

    public static final String MOCK_IP_ADDRESS = "5.199.143.67";
    public static final String MOCK_LAT = "51.2705";
    public static final String MOCK_LON = "6.8144";

    public static Mono<KeyCdnResponse> buildKeyCdnResponseMono() {
        return Mono.just(buildKeyCdnResponse());
    }

    public static KeyCdnResponse buildKeyCdnResponse() {
        KeyCdnResponse.Geo geo = new KeyCdnResponse.Geo();
        geo.setIp(MOCK_IP_ADDRESS);
        geo.setLatitude(MOCK_LAT);
        geo.setLongitude(MOCK_LON);
        KeyCdnResponse.GeoData geoData = new KeyCdnResponse.GeoData();
        geoData.setGeo(geo);
        return KeyCdnResponse.builder().data(geoData).build();
    }

    public static GeoDocument buildGeoDocument() {
        GeoDocument.Geo geo = new GeoDocument.Geo();
        geo.setIp(MOCK_IP_ADDRESS);
        geo.setLatitude(MOCK_LAT);
        geo.setLongitude(MOCK_LON);
        GeoDocument.GeoData geoData = new GeoDocument.GeoData();
        geoData.setGeo(geo);

        return GeoDocument.builder().data(geoData).build();
    }

    public static Mono<GeoDocument> buildGeoDocumentMono() {
        return Mono.just(buildGeoDocument());
    }

}
