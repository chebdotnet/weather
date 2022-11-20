package net.chebdotnet.weather.geodata;

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

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "geo-data")
public class GeoDocument {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Version
    private long version;

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
        private String countryName;
        private String countryCode;
        private String regionName;
        private String regionCode;
        private String city;
        private String postalCode;
        private String continentName;
        private String continentCode;
        private String latitude;
        private String longitude;
        private String metro_code;
        private String timezone;
        private String datetime;
    }

}
