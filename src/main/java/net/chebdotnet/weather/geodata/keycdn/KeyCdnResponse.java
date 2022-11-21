package net.chebdotnet.weather.geodata.keycdn;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyCdnResponse {
    private String status;
    private String description;
    @JsonMerge
    private GeoData data;

    @Data
    public static class GeoData {
        @JsonMerge
        private Geo geo;
    }

    @Data
    public static class Geo {
        private String host;
        private String ip;
        private String rdns;
        private String asn;
        private String isp;
        @JsonProperty("country_name")
        private String countryName;
        @JsonProperty("country_code")
        private String countryCode;
        @JsonProperty("region_name")
        private String regionName;
        @JsonProperty("region_code")
        private String regionCode;
        private String city;
        @JsonProperty("postal_code")
        private String postalCode;
        @JsonProperty("continent_name")
        private String continentName;
        @JsonProperty("continent_code")
        private String continentCode;
        private String latitude;
        private String longitude;
        @JsonProperty("metro_code")
        private String metroCode;
        private String timezone;
        private String datetime;
    }

}
