package com.ssafy.realty.realty.domain;

import com.ssafy.realty.common.TransportationType;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Value
public class Marker {
    MarkerId markerId;
    MarkerData markerData;

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

    @Value
    private static class MarkerId{
        Long value;
    }

    @Value
    public static class MarkerData{
        Double lat;
        Double lng;
        String address;
        MarkerFilter filter;

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

        @Value
        public static class MarkerFilter {
            DateRange dayRange;
            DealAmountRange dealAmountRange;
            AreaRange areaRange;
            List<Transportation> transportations;

            @Value
            public static class Range<T>{
                T lower;
                T upper;
            }

            @Value
            public static class DateRange {
                Range<LocalDate> range;
            }

            @Value
            public static class DealAmountRange {
                Range<Long> range;
            }

            @Value
            public static class AreaRange {
                Range<Double> range;
            }

            @Value
            public static class Transportation {
                TransportationType type;
                Integer time;
            }

            private static DateRange initDateRange(String lower, String upper){
                if(lower == null) lower = "1900-01-01";
                if(upper == null) upper = "2999-12-31";

                return new DateRange(new Range<>(
                        LocalDate.parse(lower, DateTimeFormatter.ISO_DATE),
                        LocalDate.parse(upper, DateTimeFormatter.ISO_DATE))
                );
            }

            private static DealAmountRange initDealAmountRange(Long lower, Long upper){
                if(lower == null) lower = 0L;
                if(upper == null) upper = 999_999_999_999L;

                return new DealAmountRange(new Range<>(lower, upper));
            }

            private static AreaRange initAreaRange(Double lower, Double upper){
                if(lower == null) lower = 0.0;
                if(upper == null) upper = 9_999_999.0;

                return new AreaRange(new Range<>(lower, upper));
            }

            private static List<Transportation> initTransportations(List<String[]> transInfo){
                List<Transportation> result = new ArrayList<>();

                for(String[] info : transInfo){
                    if(info.length != 2) throw new IllegalArgumentException("잘못된 데이터가 포함되어 있습니다.");

                    TransportationType type = TransportationType.findByTypeKey(info[0]);
                    Integer time = Integer.parseInt(info[1]);

                    result.add(new Transportation(type, time));
                }
                return result;
            }
        }
    }
}
