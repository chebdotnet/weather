package net.chebdotnet.weather.geodata.keycdn;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "services.keycdn")
public class KeyCdnProperties {
    private String url;
    private int retries;
    private int backOff;
}
