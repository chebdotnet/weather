package net.chebdotnet.weather.weatherdata.openweathermap;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "services.openweathermap")
public class OpenWeatherMapProperties {
    private String url;
    private String apiKey;
    private int retries;
    private int backOff;
}
