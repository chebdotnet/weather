package net.chebdotnet.weather.weatherdata.openweathermap;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherMapResponse {
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
    private String id;
    private String name;
    private String cod;

    @Data
    public static class Coordinates {
        private BigDecimal lon;
        private BigDecimal lat;
    }

    @Data
    public static class Weather {
        private String id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class Main {
        private String temp;
        @JsonProperty("feels_like")
        private String feelsLike;
        @JsonProperty("temp_min")
        private String tempMin;
        @JsonProperty("tempMax")
        private String temp_max;
        private String pressure;
        private String humidity;
    }

    @Data
    public static class Wind {
        private String speed;
        private String deg;
    }

    @Data
    public static class Clouds {
        private String all;
    }

    @Data
    public static class Sys {
        private String type;
        private String id;
        private String country;
        private String sunrise;
        private String sunset;
    }
}
