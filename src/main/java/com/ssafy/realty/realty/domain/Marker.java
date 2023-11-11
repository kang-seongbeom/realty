package com.ssafy.realty.realty.domain;

import com.ssafy.realty.common.TransportationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Marker {

    private MarkerId markerId;
    private MarkerData markerData;

    public static Marker init(Double lat, Double lng, String address,
                              String dateLower, String dateUpper,
                              Long dealAmountLower, Long dealAmountUpper,
                              Double areaLower, Double areaUpper,
                              List<String[]> transportations){
        return new Marker(null,
                new MarkerData(lat, lng, address,
                        MarkerData.initMarkerFilter(
                                dateLower, dateUpper,
                                dealAmountLower, dealAmountUpper,
                                areaLower, areaUpper,
                                transportations
                        )
                )
        );
    }

    @Data
    private static class MarkerId{
        private Long value;
    }

    @Data
    @AllArgsConstructor
    public static class MarkerData{
        private Double lat;
        private Double lng;
        private String address;
        private MarkerFilter filter;

        static MarkerFilter initMarkerFilter(String dateLower, String dateUpper,
                                              Long dealAmountLower, Long dealAmountUpper,
                                              Double areaLower, Double areaUpper,
                                              List<String[]> transportations){
            return new MarkerFilter(
                    MarkerData.MarkerFilter.initDateRange(dateLower, dateUpper),
                    MarkerData.MarkerFilter.initDealAmountRange(dealAmountLower, dealAmountUpper),
                    MarkerData.MarkerFilter.initAreaRange(areaLower, areaUpper),
                    MarkerData.MarkerFilter.initTransportations(transportations)
            );
        }

        @Data
        @AllArgsConstructor
        public static class MarkerFilter {
            private DateRange dayRange;
            private DealAmountRange dealAmountRange;
            private AreaRange areaRange;
            private List<Transportation> transportations;

            @Data
            @AllArgsConstructor
            public static class Range<T>{
                T lower;
                T upper;
            }

            @Data
            @AllArgsConstructor
            public static class DateRange {
                Range<LocalDate> range;
            }

            @Data
            @AllArgsConstructor
            public static class DealAmountRange {
                Range<Long> range;
            }

            @Data
            @AllArgsConstructor
            public static class AreaRange {
                Range<Double> range;
            }

            @Data
            @AllArgsConstructor
            public static class Transportation {
                TransportationType type;
                Integer time;
            }

            static DateRange initDateRange(String lower, String upper){
                if(lower == null) lower = "1900-01-01";
                if(upper == null) upper = "2999-12-31";

                return new DateRange(new Range<>(
                        LocalDate.parse(lower, DateTimeFormatter.ISO_DATE),
                        LocalDate.parse(upper, DateTimeFormatter.ISO_DATE))
                );
            }

            static DealAmountRange initDealAmountRange(Long lower, Long upper){
                if(lower == null) lower = 0L;
                if(upper == null) upper = 999_999_999_999L;

                return new DealAmountRange(new Range<>(lower, upper));
            }

            static AreaRange initAreaRange(Double lower, Double upper){
                if(lower == null) lower = 0.0;
                if(upper == null) upper = 9_999_999.0;

                return new AreaRange(new Range<>(lower, upper));
            }

            static List<Transportation> initTransportations(List<String[]> transInfo){
                List<Transportation> result = new ArrayList<>();

                for(String[] info : transInfo){
                    if(info.length != 2) throw new IllegalArgumentException("잘못된 데이터가 포함되어 있습니다.");

                    TransportationType type = TransportationType.findByValue(info[0]);
                    Integer time = Integer.parseInt(info[1]);

                    result.add(new Transportation(type, time));
                }
                return result;
            }
        }
    }
}
