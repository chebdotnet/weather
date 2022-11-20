package net.chebdotnet.weather.geodata;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chebdotnet.weather.geodata.keycdn.KeyCdnService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeoDataService {

    private final KeyCdnService keyCdnService;
    private final GeoRepository geoRepository;
    private final GeoMapper geoMapper;

    public Mono<GeoDocument> getGeoDataByIpAddress(String ipAddress) {
        return keyCdnService.getGeoDataByIp(ipAddress)
                .flatMap(entry -> {
                    GeoDocument geoDocument = geoMapper.mapGeoDataResponseToDocument(entry);
                    return Mono.zip(Mono.just(geoDocument), geoRepository.findById(entry.getData().getGeo().getIp()).defaultIfEmpty(geoDocument));
                })
                .flatMap(tuple2 -> {
                    GeoDocument savedDoc = tuple2.getT2();
                    GeoDocument newDoc = tuple2.getT1();
                    newDoc.setVersion(savedDoc.getVersion());
                    newDoc.setCreatedDate(savedDoc.getCreatedDate());
                    return geoRepository.save(newDoc);
                });
    }
}
