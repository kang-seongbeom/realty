package com.ssafy.realty.realty.dto;

import com.ssafy.realty.realty.dto.type.TransportationType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Marker {
    private Double lat;
    private Double lng;
    private String address;
    private List<MarkerFilter> filters;

    @Data
    public static class MarkerFilter {
        private DayRange dayRange;
        private DealAmountRange dealAmountRange;
        private AreaRange areaRange;
        private List<TransportationRange> transportations;

        @Data
        public static class DayRange {
            LocalDateTime lower;
            LocalDateTime upper;
        }

        @Data
        public static class DealAmountRange {
            Long lower;
            Long upper;
        }

        @Data
        public static class AreaRange {
            Double lower;
            Double upper;
        }

        @Data
        public static class TransportationRange {
            TransportationType type;
            Integer time;
        }
    }
}
