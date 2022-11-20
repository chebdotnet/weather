package net.chebdotnet.weather.weatherdata;

import net.chebdotnet.weather.weatherdata.openweathermap.OpenWeatherMapResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface WeatherMapper {

    WeatherDocument mapWeatherResponseToDocument(OpenWeatherMapResponse response);

    WeatherDto mapWeatherDocumentToWeatherDto(WeatherDocument response);

}
