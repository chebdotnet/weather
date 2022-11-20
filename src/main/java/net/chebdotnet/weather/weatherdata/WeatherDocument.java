package net.chebdotnet.weather.weatherdata;

import com.fasterxml.jackson.annotation.JsonMerge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "weather-data")
class WeatherDocument {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Version
    private long version;

    @JsonMerge
    private Coordinates coord;
    private String base;
    @JsonMerge
    private List<Weather> weather;
    @JsonMerge
    private Main main;
    private String visibility;
    @JsonMerge
    private Wind wind;
    @JsonMerge
    private Clouds clouds;
    private String dt;
    @JsonMerge
    private Sys sys;
    private String timezone;
    private String name;
    private String cod;
    @Id
    private String id;

    @Data
    public static class Coordinates implements Serializable {
        private BigDecimal lon;
        private BigDecimal lat;
    }

    @Data
    public static class Weather implements Serializable {
        private String id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class Main implements Serializable {
        private String temp;
        private String feelsLike;
        private String tempMin;
        private String tempMax;
        private String pressure;
        private String humidity;
    }

    @Data
    public static class Wind implements Serializable {
        private String speed;
        private String deg;
    }

    @Data
    public static class Clouds implements Serializable {
        private String all;
    }

    @Data
    public static class Sys implements Serializable {
        private String type;
        private String id;
        private String country;
        private String sunrise;
        private String sunset;
    }
}
