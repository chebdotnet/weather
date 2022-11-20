package net.chebdotnet.weather.weatherdata;

import lombok.RequiredArgsConstructor;
import net.chebdotnet.weather.geodata.GeoDocument;
import net.chebdotnet.weather.weatherdata.openweathermap.OpenWeatherMapService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final OpenWeatherMapService openWeatherMapService;
    private final WeatherMapper mapper;
    private final WeatherRepository weatherRepository;

    public Mono<WeatherDto> getWeatherByGeoData(GeoDocument geoDocument) {
        return openWeatherMapService.getWeatherByGeoData(geoDocument)
                .flatMap(openWeatherMapEntry -> {
                    WeatherDocument weatherDocument = mapper.mapWeatherResponseToDocument(openWeatherMapEntry);
                    return Mono.zip(Mono.just(weatherDocument),
                            weatherRepository.findById(openWeatherMapEntry.getId()).defaultIfEmpty(weatherDocument));
                })
                .flatMap(tuple2 -> {
                    WeatherDocument savedDoc = tuple2.getT2();
                    WeatherDocument newDoc = tuple2.getT1();
                    newDoc.setVersion(savedDoc.getVersion());
                    newDoc.setCreatedDate(savedDoc.getCreatedDate());
                    return weatherRepository.save(newDoc);
                })
                .map(mapper::mapWeatherDocumentToWeatherDto);
    }

}
