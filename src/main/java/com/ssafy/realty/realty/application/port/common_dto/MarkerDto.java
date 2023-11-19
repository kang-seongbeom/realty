package com.ssafy.realty.realty.application.port.common_dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkerDto {
    private Double lat;
    private Double lng;
    private String address;
    private DtoMarkerFilter filter;

    public DtoMarkerFilter getFilter() {
        return (filter != null) ? filter : new DtoMarkerFilter();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DtoMarkerFilter {
        private DtoDateRange date;
        private DtoDealAmountRange dealAmount;
        private DtoAreaRange area;
        private List<DtoTransportation> transportations;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class DtoDateRange {
            private String lower;
            private String upper;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class DtoDealAmountRange {
            private Long lower;
            private Long upper;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class DtoAreaRange {
            private Double lower;
            private Double upper;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class DtoTransportation {
            private String type;
            private Integer time;
        }
    }
}
