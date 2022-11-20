package net.chebdotnet.weather.ip;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ResponseStatus(SERVICE_UNAVAILABLE)
public class AmazonAwsServiceException extends RuntimeException {

    AmazonAwsServiceException(String message) {
        super(message);
    }

}
