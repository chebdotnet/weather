package net.chebdotnet.weather.weatherdata;

import net.chebdotnet.weather.geodata.GeoDocument;
import net.chebdotnet.weather.weatherdata.openweathermap.OpenWeatherMapResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

public class WeatherTestUtility {

    public static final String MOCK_LAT = "51.2705";
    public static final String MOCK_LON = "6.8144";
    public static final String MOCK_ID = "123";
    public static final String MOCK_NAME = "Test name";
    public static final String MOCK_BASE = "base";
    public static final String MOCK_IP_ADDRESS = "5.199.143.67";
    public static final String MOCK_TEMPERATURE = "20";
    public static final String MOCK_ALL = "0";
    public static final String MOCK_SPEED = "5.66";
    public static final String MOCK_VISIBILITY = "visibility";
    public static final String MOCK_COUNTRY = "de";
    public static final String MOCK_SUNRISE = "sunrise";
    public static final String MOCK_WEATHER_MAIN = "Clear";
    public static final String MOCK_WEATHER_DESCRIPTION = "clear sky";

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
        WeatherDocument.Coordinates coord = new WeatherDocument.Coordinates();
        coord.setLat(new BigDecimal(MOCK_LAT));
        coord.setLon(new BigDecimal(MOCK_LON));

        WeatherDocument.Main main = new WeatherDocument.Main();
        main.setTemp(MOCK_TEMPERATURE);

        WeatherDocument.Clouds clouds = new WeatherDocument.Clouds();
        clouds.setAll(MOCK_ALL);

        WeatherDocument.Wind wind = new WeatherDocument.Wind();
        wind.setSpeed(MOCK_SPEED);

        WeatherDocument.Sys sys = new WeatherDocument.Sys();
        sys.setCountry(MOCK_COUNTRY);
        sys.setSunrise(MOCK_SUNRISE);

        WeatherDocument.Weather weather = new WeatherDocument.Weather();
        weather.setMain(MOCK_WEATHER_MAIN);
        weather.setDescription(MOCK_WEATHER_DESCRIPTION);

        return WeatherDocument.builder()
                .id(MOCK_ID)
                .name(MOCK_NAME)
                .base(MOCK_BASE)
                .visibility(MOCK_VISIBILITY)
                .coord(coord)
                .main(main)
                .clouds(clouds)
                .wind(wind)
                .sys(sys)
                .weather(List.of(weather))
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

        WeatherDto.Clouds clouds = new WeatherDto.Clouds();
        clouds.setAll(MOCK_ALL);

        WeatherDto.Wind wind = new WeatherDto.Wind();
        wind.setSpeed(MOCK_SPEED);

        WeatherDto.Sys sys = new WeatherDto.Sys();
        sys.setCountry(MOCK_COUNTRY);
        sys.setSunrise(MOCK_SUNRISE);

        WeatherDto.Weather weather = new WeatherDto.Weather();
        weather.setMain(MOCK_WEATHER_MAIN);
        weather.setDescription(MOCK_WEATHER_DESCRIPTION);

        return WeatherDto.builder()
                .base(MOCK_BASE)
                .visibility(MOCK_VISIBILITY)
                .coord(coord)
                .main(main)
                .clouds(clouds)
                .wind(wind)
                .sys(sys)
                .weather(List.of(weather))
                .build();
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
