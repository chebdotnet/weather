package net.chebdotnet.weather.weatherdata.openweathermap;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class OpenWeatherMapException extends RuntimeException {

    OpenWeatherMapException(String message) {
        super(message);
    }

}
