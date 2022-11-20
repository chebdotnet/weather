package net.chebdotnet.weather.weatherdata;

import net.chebdotnet.weather.geodata.GeoDocument;
import net.chebdotnet.weather.weatherdata.openweathermap.OpenWeatherMapResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class WeatherTestUtility {

    public static final String MOCK_LAT = "51.2705";
    public static final String MOCK_LON = "6.8144";
    public static final String MOCK_ID = "123";
    public static final String MOCK_NAME = "Test name";
    public static final String MOCK_BASE = "base";
    public static final String MOCK_IP_ADDRESS = "5.199.143.67";
    public static final String MOCK_TEMPERATURE = "20";

    public static OpenWeatherMapResponse buildOpenWeatherMapResponse() {
        OpenWeatherMapResponse.Coordinates coordinates = new OpenWeatherMapResponse.Coordinates();
        coordinates.setLat(new BigDecimal(MOCK_LAT));
        coordinates.setLon(new BigDecimal(MOCK_LON));
        return OpenWeatherMapResponse.builder().coord(coordinates).build();
    }

    public static Mono<OpenWeatherMapResponse> buildOpenWeatherMapResponseMono() {
        return Mono.just(buildOpenWeatherMapResponse());

    }

    static WeatherDocument buildWeatherDocument() {
        return WeatherDocument.builder()
                .id(MOCK_ID)
                .name(MOCK_NAME)
                .base(MOCK_BASE)
                .build();
    }

    static Mono<WeatherDocument> buildWeatherDocumentMono() {
        return Mono.just(buildWeatherDocument());
    }


    static WeatherDto buildWeatherDto() {
        WeatherDto.Coordinates coord = new WeatherDto.Coordinates();
        coord.setLat(new BigDecimal(MOCK_LAT));
        coord.setLon(new BigDecimal(MOCK_LON));

        WeatherDto.Main main = new WeatherDto.Main();
        main.setTemp(MOCK_TEMPERATURE);
        return WeatherDto.builder().coord(coord).main(main).base(MOCK_BASE).build();
    }

    static Mono<WeatherDto> buildWeatherDtoMono() {
        return Mono.just(buildWeatherDto());
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

    static Mono<GeoDocument> buildGeoDocumentMono() {
        return Mono.just(buildGeoDocument());
    }

}
