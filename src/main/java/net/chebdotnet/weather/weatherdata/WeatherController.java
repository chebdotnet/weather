package net.chebdotnet.weather.weatherdata;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chebdotnet.weather.geodata.GeoDataService;
import net.chebdotnet.weather.ip.IpResolverService;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final GeoDataService geoDataService;
    private final WeatherService weatherService;
    private final IpResolverService ipResolverService;

    @GetMapping
    Mono<WeatherDto> getWeatherByIp(ServerHttpRequest request) {
        return ipResolverService.resolve()
                .flatMap(geoDataService::getGeoDataByIpAddress)
                .flatMap(weatherService::getWeatherByGeoData);
    }

}
