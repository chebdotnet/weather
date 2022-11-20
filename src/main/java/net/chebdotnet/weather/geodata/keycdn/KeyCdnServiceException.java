package net.chebdotnet.weather.geodata.keycdn;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class KeyCdnServiceException extends RuntimeException {

    KeyCdnServiceException(String message) {
        super(message);
    }

}
