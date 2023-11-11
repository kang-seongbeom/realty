package com.ssafy.realty.realty.domain;

import com.ssafy.realty.realty.domain.type.TransportationType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Marker {

    private MarkerId markerId;
    private MarkerData markerData;

    @Data
    private static class MarkerId{
        private Long value;
    }

    @Data
    private static class MarkerData{
        private Double lat;
        private Double lng;
        private String address;
        private MarkerFilter filters;

        @Data
        public static class MarkerFilter {
            private DateRange dayRange;
            private DealAmountRange dealAmountRange;
            private AreaRange areaRange;
            private List<TransportationRange> transportations;

            @Data
            public static class Range<T>{
                T lower;
                T upper;
            }

            @Data
            public static class DateRange {
                Range<LocalDate> range;
            }

            @Data
            public static class DealAmountRange {
                Range<Long> range;
            }

            @Data
            public static class AreaRange {
                Range<Double> range;
            }

            @Data
            public static class TransportationRange {
                TransportationType type;
                Integer time;
            }
        }
    }
}
