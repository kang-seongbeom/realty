package com.ssafy.realty.realty.adapter.in.web.payload;

import lombok.Data;

import java.util.List;

@Data
public class RealtyInfoPayload {
    private Double lat;
    private Double lng;
    private String address;
    private PayloadMarkerFilter filters;

    @Data
    private static class PayloadMarkerFilter {
        private DateRange dayRange;
        private DealAmountRange dealAmountRange;
        private AreaRange areaRange;
        private List<Transportation> transportations;

        @Data
        private static class Range<T>{
            private T lower;
            private T upper;
        }

        @Data
        private static class DateRange {
            private Range<String> range;
        }

        @Data
        private static class DealAmountRange {
            private Range<Long> range;
        }

        @Data
        private static class AreaRange {
            private Range<Double> range;
        }

        @Data
        private static class Transportation{
            private String type;
            private Integer time;
        }
    }
}
