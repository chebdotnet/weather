package net.chebdotnet.weather.weatherdata;

import net.chebdotnet.weather.weatherdata.openweathermap.OpenWeatherMapResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static net.chebdotnet.weather.weatherdata.WeatherTestUtility.buildOpenWeatherMapResponse;
import static net.chebdotnet.weather.weatherdata.WeatherTestUtility.buildWeatherDocument;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class WeatherMapperTest {

    private WeatherMapper weatherMapper = new WeatherMapperImpl();

    @Test
    @DisplayName("Should map weather document to weather dto")
    void shouldMapWeatherResponseToDocument() {
        OpenWeatherMapResponse openWeatherMapResponse = buildOpenWeatherMapResponse();
        WeatherDocument weatherDocument = weatherMapper.mapWeatherResponseToDocument(openWeatherMapResponse);
        assertEquals(openWeatherMapResponse.getCoord().getLat(), weatherDocument.getCoord().getLat());
        assertEquals(openWeatherMapResponse.getCoord().getLon(), weatherDocument.getCoord().getLon());
    }

    @Test
    @DisplayName("Should map weather document to weather dto")
    void shouldMapWeatherDocumentToWeatherDto() {
        WeatherDocument weatherDocument = buildWeatherDocument();
        WeatherDto weatherDto = weatherMapper.mapWeatherDocumentToWeatherDto(weatherDocument);
        assertEquals(weatherDto.getId(), weatherDocument.getId());
        assertEquals(weatherDto.getName(), weatherDocument.getName());
    }
}
