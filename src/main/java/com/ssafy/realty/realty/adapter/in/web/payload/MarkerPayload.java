package com.ssafy.realty.realty.adapter.in.web.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MarkerPayload {
    @NotNull
    private Double lat;
    @NotNull
    private Double lng;
    @NotNull
    private String address;

    private PayloadMarkerFilter filter;

    @Data
    public static class PayloadMarkerFilter {
        private PayloadDateRange date;
        private PayloadDealAmountRange dealAmount;
        private PayloadAreaRange area;
        private List<PayloadTransportation> transportations;

        @Data
        private static class PayloadDateRange {
            private String lower;
            private String upper;
        }

        @Data
        private static class PayloadDealAmountRange {
            private Long lower;
            private Long upper;
        }

        @Data
        private static class PayloadAreaRange {
            private Double lower;
            private Double upper;
        }

        @Data
        private static class PayloadTransportation {
            private String type;
            private Integer time;
        }
    }
}
