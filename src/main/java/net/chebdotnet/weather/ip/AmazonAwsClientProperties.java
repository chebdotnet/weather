package net.chebdotnet.weather.ip;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "services.amazonaws")
public class AmazonAwsClientProperties {
    private String url;
    private int retries;
    private int backOff;
}
