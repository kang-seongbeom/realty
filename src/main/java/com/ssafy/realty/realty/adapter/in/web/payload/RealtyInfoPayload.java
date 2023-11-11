package com.ssafy.realty.realty.adapter.in.web.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RealtyInfoPayload {
    @NotNull
    private Double lat;
    @NotNull
    private Double lng;
    @NotNull
    private String address;

    private PayloadMarkerFilter filter;

    @Data
    private static class PayloadMarkerFilter {
        private DateRange date;
        private DealAmountRange dealAmount;
        private AreaRange area;
        private List<Transportation> transportations;

        @Data
        private static class DateRange {
            private String lower;
            private String upper;
        }

        @Data
        private static class DealAmountRange {
            private Long lower;
            private Long upper;
        }

        @Data
        private static class AreaRange {
            private Double lower;
            private Double upper;
        }

        @Data
        private static class Transportation{
            private String type;
            private Integer time;
        }
    }
}
