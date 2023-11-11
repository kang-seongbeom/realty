package com.ssafy.realty.realty.application.port.in.dto;

import lombok.*;

import java.util.List;

@Data
public class MarkerDto {
    private Double lat;
    private Double lng;
    private String address;
    private DtoMarkerFilter filter;

    @Data
    public static class DtoMarkerFilter {
        private PayloadDateRange date;
        private PayloadDealAmountRange dealAmount;
        private PayloadAreaRange area;
        private List<PayloadTransportation> transportations;

        @Data
        public static class PayloadDateRange {
            private String lower;
            private String upper;
        }

        @Data
        public static class PayloadDealAmountRange {
            private Long lower;
            private Long upper;
        }

        @Data
        public static class PayloadAreaRange {
            private Double lower;
            private Double upper;
        }

        @Data
        public static class PayloadTransportation {
            private String type;
            private Integer time;
        }
    }
}
