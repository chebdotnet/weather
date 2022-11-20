package net.chebdotnet.weather.geodata;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoRepository extends ReactiveCrudRepository<GeoDocument, String> {
}
