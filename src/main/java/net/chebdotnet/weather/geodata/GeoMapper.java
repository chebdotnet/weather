package net.chebdotnet.weather.geodata;

import net.chebdotnet.weather.geodata.keycdn.KeyCdnResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GeoMapper {

    @Mapping(target = "id", source = "data.geo.ip")
    GeoDocument mapGeoDataResponseToDocument(KeyCdnResponse response);

}
