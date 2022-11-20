package net.chebdotnet.weather.weatherdata;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface WeatherRepository extends ReactiveCrudRepository<WeatherDocument, String> {
}
