package net.chebdotnet.weather.weatherdata;

import lombok.extern.slf4j.Slf4j;
import net.chebdotnet.weather.geodata.keycdn.KeyCdnServiceException;
import net.chebdotnet.weather.ip.AmazonAwsServiceException;
import net.chebdotnet.weather.weatherdata.openweathermap.OpenWeatherMapException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@Slf4j
@ControllerAdvice
public class WeatherExceptionHandler {

    @ExceptionHandler(value = KeyCdnServiceException.class)
    public void defaultErrorHandler(KeyCdnServiceException e) {
        log.warn("Returning an exception calling KeyCdnService service: {}", e.getMessage(), e);
        throw e;
    }

    @ExceptionHandler(value = OpenWeatherMapException.class)
    public void defaultErrorHandler(OpenWeatherMapException e) {
        log.warn("Returning an exception calling OpenWeatherMapService service: {}", e.getMessage(), e);
        throw e;
    }

    @ExceptionHandler(value = AmazonAwsServiceException.class)
    public void defaultErrorHandler(AmazonAwsServiceException e) {
        log.warn("Returning an exception calling AmazonAwsServiceException service: {}", e.getMessage(), e);
        throw e;
    }


}
